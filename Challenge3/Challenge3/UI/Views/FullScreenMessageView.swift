import SwiftUI

struct FullScreenMessageView: View {
    
    let message: String
    
    var body: some View {
        VStack {
            Spacer()
            Text(message.localize())
                .foregroundColor(.secondary)
                .font(.title2)
                .padding()
            Spacer()
        }
    }
}

struct FullScreenMessageView_Previews: PreviewProvider {
    static var previews: some View {
        FullScreenMessageView(message: "Error")
    }
}
