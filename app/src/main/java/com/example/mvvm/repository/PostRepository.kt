package com.example.mvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.api.PostRemoteDataSource
import com.example.mvvm.database.PostEntity
import com.example.mvvm.database.PostLocalDataSource
import com.example.mvvm.model.Post
import com.example.mvvm.model.utility.NetworkHelper
import com.example.mvvm.model.utility.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PostRepository(
    private val networkHelper: NetworkHelper,
    private val localDataSource: PostLocalDataSource,
    private val remoteDataSource: PostRemoteDataSource
) {
    private val posts = MutableLiveData<NetworkResult<List<PostEntity>>>()

    init {
        getPosts()
    }

    private fun getPosts() {
        /*
        Internet holatini tekshiramiz! Agar bor bo'lsa remote orqali ma'lumotni olib, databasega
        saqlab local cache orqali natijani yuboramiz. Agar internet yo'q bo'lsa, databasedan
        oxirgi saqlangan ma'lumotlarni yuboramiz. Coroutine yordamida olib kelish shart!
         */

        if (networkHelper.isNetworkConnected()) {
            Log.d("TTT", "Network connected")
            CoroutineScope(Dispatchers.IO).launch {
                posts.postValue(NetworkResult.loading())
                try {
                    coroutineScope {
                        val deferredResult: Deferred<List<Post>> =
                            async { remoteDataSource.getAllPosts() }
                        val list = deferredResult.await()

                        val cacheList: MutableList<PostEntity> = ArrayList()
                        for (post in list) {
                            val postEntity = PostEntity(post.id, post.userId, post.title, post.body)
                            cacheList.add(postEntity)
                        }
                        localDataSource.cachePosts(cacheList)
                        posts.postValue(NetworkResult.success(cacheList))
                    }
                } catch (e: Exception) {
                    posts.postValue(NetworkResult.error(e.message ?: "Error occurred"))
                }
            }
        } else {
            Log.d("TTT", "not connected")
            posts.postValue(NetworkResult.success(localDataSource.getAllPosts().value))
        }
    }

    fun getAllPosts(): LiveData<NetworkResult<List<PostEntity>>> {
        return posts
    }
}