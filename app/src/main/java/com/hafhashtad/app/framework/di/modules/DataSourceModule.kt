package  com.hafhashtad.app.framework.di.modules

import com.hafhashtad.app.framework.database.DatabaseModule
import com.hafhashtad.app.framework.network.NetworkModule
import com.hafhashtad.app.framework.network.RemoteStoreDataSource
import com.hafhashtad.app.framework.network.RemoteStoreService
import com.hafhashtad.app.framework.network.mapper.CategoryResponseMapper
import com.hafhashtad.app.framework.network.mapper.ProductResponseMapper
import com.hafhashtad.core.data.StoreDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteStoreDataSource(
        service: RemoteStoreService,
        productResponseMapper: ProductResponseMapper,
        categoryResponseMapper: CategoryResponseMapper
    ): StoreDataSource {
        return RemoteStoreDataSource(service, productResponseMapper, categoryResponseMapper)
    }
}
