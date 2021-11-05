import Combine
import Foundation
import shared
import KMPNativeCoroutinesCombine

typealias KmmBusinessSearchInteractor = shared.BusinessSearchInteractor

class BusinessSearchInteractor {
    
    private let interactor: KmmBusinessSearchInteractor
    
    init(interactor: KmmBusinessSearchInteractor) {
        self.interactor = interactor
    }
    
    func searchOpenBusinesses(
        query: String,
        latitude: Double,
        longitude: Double
    ) -> AnyPublisher<[BusinessModel], Error> {
        return createPublisher(
            for: interactor.searchOpenBusinessesNative(query: query, latitude: latitude, longitude: longitude)
        )
            .eraseToAnyPublisher()
    }
}
