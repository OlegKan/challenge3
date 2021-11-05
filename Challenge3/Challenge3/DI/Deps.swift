import Foundation
import shared

class Deps {
    static let shared = Deps()
    
    private let yelpApi: YelpApi =  YelpApi()
    
    // MARK: - search
    let businessSearchInteractor: BusinessSearchInteractor
    private let kmmBusinessSearchInteractor: KmmBusinessSearchInteractor
    private let businessRepository: BusinessRepository
    private let businessRemoteDataSource: BusinessDataSource
    private let businessMemoryCache: BusinessCache
    private let businessDataSourceFactory: BusinessDataSourceFactory

    private init() {
        
        // MARK: - search
        businessMemoryCache = BusinessMemoryCache() as! BusinessCache
        businessRemoteDataSource = BusinessRemoteDataSource(yelpApi: yelpApi, cache: businessMemoryCache)
        businessDataSourceFactory = BusinessDataSourceFactory(
            remoteDataSource: businessRemoteDataSource,
            localCache: businessMemoryCache
        )
        businessRepository = BusinessRepositoryImpl(dataSourceFactory: businessDataSourceFactory)

        kmmBusinessSearchInteractor = KmmBusinessSearchInteractor(businessRepository: businessRepository)
        businessSearchInteractor = BusinessSearchInteractor(interactor: kmmBusinessSearchInteractor)
    }
}
