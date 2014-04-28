 
	server {
		listen       80;
		server_name  ${address};

		#charset koi8-r;

		#access_log  logs/host.access.log  main;

		location / {
			root	${uploadDir}vipsite/${username}/html;
			index	index.htm index.html;
			error_page 404	/nginx404.html;
			log_not_found	off;
		}
		 location ~ ^/search {
			root	${uploadDir}vipsite/${username}/html;
			index	index.htm index.html;
			error_page	404 = @search;
			log_not_found	off;
		}
		location @search {
			proxy_pass http://abbcc.net;	
			proxy_set_header http_host $host;
			rewrite	^/search/([^,]*),([^,]*),([^,]*)\.html$ /viplate/${username}/search?keywords=$1&priceStart=$2&priceEnd=$3 break;
			rewrite	"^/search/([^;]*);([^;]*);([^;]*)\.html$" /rest/static/${username}/search?keywords=$1&priceStart=$2&priceEnd=$3 break;
		}
	
		rewrite ^/([^-/]+)-([^-/]+)\.html$ /$1/$2.html last;
		rewrite ^/([^-/]+)-([^-/]+)-([^-/]+)\.html$ /$1/$2/$3.html last;
	
	}