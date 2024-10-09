# Seminar 2
onCreate() -> ocupa spatiul de memorie pt activitatea aferenta
onStart() -> afiseaza activitatea pe ecran
onResume() -> face activitea activa, devine activa

onPause() -> devine inactiva dar vizibila
onStop() -> nu mai e nici vizibila
onDestroy() => o distruge din memorie acitvitatea

Aplicatiile sunt adaugate in stiva, in mom in care nu mai are memorie unde sa deschida aplicatie, ia stiva de activitati
si daca nu e folosita un anumit timp se apleaza onDestroy() pe aplicatia respectiva.

Log.e() -> log de eroare
lOG.W()-> log de warning
log.d() -> log de debugging
log.i()-> log de info

Cand se lanseaza sunt apelate onCreate(), onStart(), onResume()
Context -> reprezinta contextul, adica activitatea curenta in acel moment.
