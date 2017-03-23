public class Download_urls {
    public Download_urls(){
    }
    
    public void download(String[] urls, String es_index, String es_doc_type, String es_server){
	Download download = new Download("uploaded", es_index, es_doc_type, es_server);

	try
	{
	for(String url: urls){
	    download.addTask(Download_Utils.validate_url(url));
	}
	}
	finally
	{
	download.shutdown();
	}
	

    }

    public static void main(String[] args) {
	
	String urls_str = ""; //default
	String es_index = "memex";
	String es_doc_type = "page";
	String es_server = "localhost";
	
	int i = 0;
	while (i < args.length){
	    String arg = args[i];
	    if(arg.equals("-u")){
		urls_str = args[++i];
	    } else if(arg.equals("-i")){
		es_index = args[++i];
	    } else if(arg.equals("-d")){
		es_doc_type = args[++i];
	    } else if(arg.equals("-s")){
		es_server = args[++i];
	    }else {
		System.out.println("Unrecognized option");
		break;
	    }
	    ++i;
	}

	String[] urls = null;
	if(urls_str != null & !urls_str.isEmpty())
	    urls = urls_str.split(" ");
		
	Download_urls download_urls = new Download_urls();
	download_urls.download(urls, es_index, es_doc_type, es_server);
    }
}
