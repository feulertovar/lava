import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowseWeb {
	
	private String url;
	public BrowseWeb() {
		// TODO Auto-generated constructor stub
		url = "http://www.feulermvp.com";
	}
	public void Browser ( String url, MouseEvent evt) throws IOException {
		
		//add Url Prefix
		url = "http://" + url;

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	public void setUrl( String myUrl) {
		this.url = myUrl;
	}
	
	public String getUrl() {
		return url;
	}

}
