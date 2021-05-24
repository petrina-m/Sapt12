# FAZAN
Aplicatia fazan este o aplicatie web realizata cu SpringBoot, in care 2 jucatori (clienti care comunica cu serverul cu ajutorul unui Web Socket, protocolul STOMP) se pot conecta online si juca Fazan in engleza.
Dupa ce se conecteaza primul jucator, este creat un joc nou in baza de date. Al doilea jucator se poate conecta la joc prin id-ul jocului pe care il poate primi de la primul jucator, sau se poate conecta la un joc random(primul joc gasit in baza de date care nu are inca 2 jucatori).

O data ce incepe jocul, cei doi players, introduc pe rand cate un cuvant. Sincronizarea dintre cei doi clienti este facuta din interiorul JavaScript-ului astfel incat la un moment dat, doar un jucator poate introduce un cuvant, celalalt player fiind fortat sa astepte. O data ce un player introduce un cuvant nou, este trimis un request nou catre server care contine cuvantul, id-ul playeruluui care a introdus acest cuvant si id-ul jocului. Apoi, se verifica daca acest cuvant e corect: 
    Se verifica daca cuvantul a mai fost folosit anterior in cadrul aceluiasi joc
    Se verifica daca cuvantul este unul corect (apare in dictionar)
    Se verifica daca cuvantul are cel putin 2 litere
    Se verifica daca cuvantul (in cazul in care acesta nu este primul cuvant din cadrul jocului) respecta regula conform careia ultimele 2 litere din cuvantul precedent trebuie sa corespunda cu primele 2 din noul cuvant
     In cazul in care ultimul cuvant e gresit, jucatorul care l-a introdus pierde jocul.

Interfata este facuta cu HTML,CSS si JavaScript.

Tehnologii folosite:
    Modelarea jocului cu ajutorul claselor, obiectelor, interfetelor si enum
    Exceptii (crearea unor exceptii noi customizate si utilizarea acestora)
    Lucrul cu fisiere (dictionarul este un document pe care il citim si il introduc intr-un HashSet pentru a realiza dupa operatiile necesare)
    JPA (Hibernate, am utilizat postgreSQL pentru a stoca datele despre jocuri si jucatori)
    REST Services (aplicatia realizata SpringBoot, dupa modelul MVC). Am utilizat urmatoarele depedente: SpringWeb, WebSocket, PostgreSQL Driver si SpringData JPA.

