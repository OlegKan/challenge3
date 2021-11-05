import Foundation
import UIKit

final class Browser {
    
    static func openUrl(_ string: String) {
        guard let url = URL(string: string) else { return }
        UIApplication.shared.open (url)
    }
    
    static func openAppStore(_ string: String) {
        guard let url = URL(string: string) else { return }
        let activityView = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController?.present(activityView, animated: true, completion: nil)
    }
}
