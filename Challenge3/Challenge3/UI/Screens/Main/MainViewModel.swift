import Combine
import Foundation
import shared

class MainViewModel: ObservableObject {
    
    @Published var searchResult: [BusinessModel] = []
    @Published var isLoading: Bool = true
        
    private let searchInteractor = Deps.shared.businessSearchInteractor
    private var cancellableSet: Set<AnyCancellable> = []
    
    init() {
        search()
    }
    
    func onItemTap(_ model: BusinessModel) {
        //TODO
    }
    
    private func search() {
        setProgress(isLoading: true)
        
        searchInteractor.searchOpenBusinesses(
            query: "pizza, beer",
            latitude: 37.767016,
            longitude: -122.421842
        )
            .receive(on: DispatchQueue.main)
            .sink(
                receiveCompletion: { _ in self.setProgress(isLoading: false) },
                receiveValue: { self.onSearchReceivedValue(searchResult: $0) }
            )
            .store(in: &cancellableSet)
    }
    
    private func onSearchReceivedValue(searchResult: [BusinessModel]) {
        self.searchResult = searchResult
    }
    
    private func setProgress(isLoading: Bool) {
        self.isLoading = isLoading
    }
}
