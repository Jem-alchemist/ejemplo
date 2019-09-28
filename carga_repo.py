import urequests
url_base ='https://raw.githubusercontent.com/Jem-alchemist/ejemplo/master/'                                                                
r = urequests.get(url_base + 'hola.py')                                              
exec (r.text)
url_base ='https://raw.githubusercontent.com/Jem-alchemist/ejemplo/master/'                                                                
r = urequests.get(url_base + 'lista.py')                                              
exec (r.text)

