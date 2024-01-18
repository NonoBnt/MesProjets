#ifndef LISTEDOUBLE_H_INCLUDED
#define LISTEDOUBLE_H_INCLUDED

#include <stdbool.h>
#include "towerdefend.h"


/*
typedef struct T_cell{
    struct T_cell *suiv;
    struct T_cell *prec;
    Tunite *pdata;              //attention à faire un malloc sur ce champ avant de s'en servir
} T_cellule;
typedef T_cellule *TListePlayer;
*/

void initListe(TListePlayer *l);
bool listeVide( TListePlayer l);



void afficheListeV1( TListePlayer l);
//Pour "AfficheListeV1" Vous avez le droit de lire directement dans la structure de données
//Utile pour afficher et debuguer les fonctions ci-dessous
void ajoutEnTetePtr2Ptr(TListePlayer *l, Tunite mydata);

TListePlayer ajoutEnTete(TListePlayer l, Tunite mydata); //Bien mettre à NULL les champs suiv et prec non utilisés s'il y en a
TListePlayer ajoutEnFin(TListePlayer l, Tunite mydata);
TListePlayer ajoutEnN(TListePlayer l, int pos, Tunite mydata);

TListePlayer suppEnTete(TListePlayer l);
TListePlayer suppEnFin(TListePlayer l);
TListePlayer suppEnN(TListePlayer l, int pos);

TListePlayer getptrFirstCell(TListePlayer l);
TListePlayer getptrLastCell(TListePlayer l);
TListePlayer getptrNextCell(TListePlayer l);
TListePlayer getptrPrevCell(TListePlayer l);

Tunite* getPtrData(TListePlayer l);
void swapPtrData( TListePlayer source, TListePlayer destination );

int getNbreCell(TListePlayer l);
int getSizeBytes(TListePlayer l); //utilisation de sizeof

TListePlayer creatNewListeFromFusion(TListePlayer l1, TListePlayer l2); //on souhaite CREER une nouvelle liste sans modifier l1 et l2
TListePlayer addBehind(TListePlayer debut, TListePlayer suite);

TListePlayer findCell(TListePlayer l, Tunite data, bool (*fcomp)(Tunite a, Tunite b) );
int getOccurences(TListePlayer l, int data);  //nbre de fois que data est présent dans toute la liste l1

void afficheListeV2( TListePlayer l);
//Pour "AfficheListeV2", vous devez utiliser uniquement les fonctions écrites ci-dessus (interdiction de passer directement par les champs ptr de la SD)


//*********************************************************
//***************fonctions etapes 2************************
//*********************************************************

TListePlayer listeNbrAlea(int n);
void affichetab(int *tab, int len);

Tunite *converttab(TListePlayer l, int taille);
Tunite *converttabfree(TListePlayer l, int taille);

TListePlayer tabintoliste(Tunite *tab, int len);
void tri_selection(Tunite *tableau, int taille);

TListePlayer triliste(TListePlayer l);

//**************************************************
//******************Etape 3*************************
//**************************************************

void tri_selection_liste(TListePlayer l);


//**************************************************
//******************Etape 4*************************
//**************************************************

TListePlayer creerListeNElem(int taille);

void print (Tunite *p);



#endif // LISTEDOUBLE_H_INCLUDED

