# DemoDevelopment
This is the webcrawler. 
Given a starting URL –say example.com -it will visit all pages within the domain and collects all images,
but not follow the links to external sites other than  this domain.

input:
Any valid URL.
Example: http://www.novelglow.com

Output:
JSON structure containing all the URLs in the given site and images within corresponding URLs.
Example: {"FinalResult":[{"http://www.novelglow.com/":[{"image0":"http://www.novelglow.com/wp-content/uploads/2016/02/ng_2_logo.png"}]},
{"http://www.novelglow.com/":[{"image0":"http://www.novelglow.com/wp-content/uploads/2016/02/ng_2_logo.png"}]}]}

Futurework:
This webcrawler can be used to get and score the keywords,topics,sentiments in the pages and hence build data analytics.
This data can be used for developing a search engine,e-commerce and so on.

Dependencies:
1. Java 1.6 or above.
2. commons-beanutils-1.7.0
3. commons-collections-3.2.1
4. commons-lang-2.4
5. commons-logging-1.1.3
6. commons-validator-1.5.1
7. ezmorph
8. json-lib-2.4-jdk15
9. jsoup-1.10.2
