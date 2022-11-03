# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Sander Wiik hjermstad, S362119, s362119@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å bruke en while-løkke med en comparator og gjør p=null slik at q er den
siste noden jeg passerer. Setter rot hvis q=null, setter venstre/eller høyrebarn.

I oppgave 2 laget jeg en while-løkke for å sammenligne verdiene (c) i treet med hjelpevariabelen antallVerdi.

Oppgave 3 løste jeg ved i førstePostden å sjekke om p, p.høyre og p.venstre ikke er null. Deretter laget jeg 
nestePostorden og satte f til p.forelder. Sjekket om f.høyre er null eller p, kaller på førstePostorden dersom 
det ikke er det. 

I oppgave 4, postorden, kalte jeg på førstePostorden og lot første være den. Deretter brukte jeg en while-løkke som løper
gjennom nestePostorden med første som parameter og utfører oppgave så lenge forelder til første ikke er null.
I postordenRecursive kaller jeg den selv for p sitt venstre- og høyrebarn og utfører oppgaven. 

I oppgave 5 for serialize() brukte jeg en kø og while-løkke for å ta ut verdien av toppen av køa, og legge den 
inn i arrayet, samt høyre- og venstrebarn inn i køen dersom de finnes. I deserialize() brukte jeg er for-løkke som
går gjennom treet og bruker leggInn() for hver nye verdi. 




