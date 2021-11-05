import SwiftUI
import shared

struct MainView: View {
    
    @ObservedObject var viewModel: MainViewModel
    
    var body: some View {
        return NavigationView {
            ZStack {
                List {
                    ForEach(viewModel.searchResult, id: \.self) { model in
                        ListItem(model: model) {
                            onItemTap(model)
                        }
                    }
                }
                ActivityIndicatorView(isLoading: viewModel.isLoading)
            }
            .listStyle(PlainListStyle())
        }
    }
    
    private func onItemTap(_ model: BusinessModel) {
        viewModel.onItemTap(model)
    }
}

private struct ListItem: View {
    let model: BusinessModel
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            VStack(alignment: .leading) {
                Text(model.name)
                HStack {
                    Text(model.type)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                    Spacer()
                    Text(model.formattedDistance)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
            }
        }
    }
}
