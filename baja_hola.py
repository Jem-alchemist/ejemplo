import urequests
url_base ='https://raw.githubusercontent.com/Jem-alchemist/ejemplo/master/'                                                                
r = urequests.get(url_base + 'hola.py')                                              
f = open('data.txt', 'w')
f.write('hola.py')
f.close()
