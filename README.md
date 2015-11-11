# SIMOV 2014/2015 - IntoxicatedAPP #

### Introdução ###

* Código-fonte do projeto "IntoxicatedAPP" desenvolvido no âmbito das disciplinas ARQAM e SIMOV.
* Versão atual: 1.0

### Requisitos ###

* Android 4.0.4 ou superior
* Google Play Services
* Acesso a GPS
* Acesso a Wi-fi
* Node.JS (para o servidor externo)

### Instalação (com o ficheiro apk) ###

* Transferir o ficheiro IntoxicatedApp.apk para o Android.
* Nas configurações do Android, na secção "Segurança", escolher a opção para permitir a instalação de aplicações de fontes desconhecidas.
* Instalar IntoxicatedApp.apk.

### Instalação (pelo ADT Eclipse) ###

* Ter o projeto google-play-services_lib aberto.
* Importar o projeto IntoxicatedApp para o eclipse. 
* Correr o projeto como aplicação android.

### Execução do servidor Node ###

* O servidor foi desenvolvido em [Node.JS](http://nodejs.org/) para a sua execução, basta abrir uma consola no directório do servidor e executar o comando: "node server.js"
* Para obter os conselhos na aplicação, basta introduzir o endereço do servidor (ex. http://192.168.1.3:9000/advices) nas definições da aplicação.

### Configuração ###

* A aplicação permite definir até três contactos, para tal basta aceder as definições e escolher contactos da lista, ou inseri-los manualmente.
* É tambem possível inserir um endereço para adquirir os conselhos de uma base de dados externa (devido a impossibilidade de alojamento do servidor num endereço fixo).

### Autores ###

* Mário Teixeira [1090626@isep.ipp.pt](mailto:1090626@isep.ipp.pt)
* Marta Graça [1100640@isep.ipp.pt](mailto:1100640@isep.ipp.pt)
* Sara Rego [1110637@isep.ipp.pt](mailto:1110637@isep.ipp.pt)