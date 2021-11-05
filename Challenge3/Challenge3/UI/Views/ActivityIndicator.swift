import SwiftUI

struct ActivityIndicatorView: View {
    let isLoading: Bool
    
    var body: some View {
        ActivityIndicator(isAnimating: .constant(true), style: .large)
            .padding(30)
            .background(Color.secondary.colorInvert())
            .foregroundColor(Color.primary)
            .cornerRadius(20)
            .opacity(isLoading ? 1 : 0)
    }
}

struct ActivityIndicator: UIViewRepresentable {
    
    @Binding var isAnimating: Bool
    let style: UIActivityIndicatorView.Style
    
    func makeUIView(context: UIViewRepresentableContext<ActivityIndicator>) -> UIActivityIndicatorView {
        return UIActivityIndicatorView(style: style)
    }
    
    func updateUIView(_ uiView: UIActivityIndicatorView, context: UIViewRepresentableContext<ActivityIndicator>) {
        isAnimating ? uiView.startAnimating() : uiView.stopAnimating()
    }
}

struct ActivityIndicator_Previews: PreviewProvider {
    
    private struct PreviewView: View {
        @State var isAnimating: Bool = true
        
        var body: some View {
            ActivityIndicator(isAnimating: $isAnimating, style: .medium)
        }
    }
    
    static var previews: some View {
        PreviewView()
    }
}
