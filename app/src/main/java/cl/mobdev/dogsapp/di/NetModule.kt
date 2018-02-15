package cl.mobdev.dogsapp.di

import android.content.Context
import cl.mobdev.dogsapp.BuildConfig
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

@Module
class NetModule {

    @Provides
    @Reusable
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dog.ceo/api/")
            .build()

    @Provides
    @Reusable
    fun providePicasso(context: Context, client: OkHttpClient) = Picasso.Builder(context)
            .downloader(OkHttp3Downloader(client))
            .build()

}
