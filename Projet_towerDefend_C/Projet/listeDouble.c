#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "listeDouble.h"
#include "towerdefend.h"




//initListe ne fait pas de malloc, juste une initialisation à NULL du pointeur de liste
void initListe(TListePlayer *l){
*l=NULL;
}

//fonction qui dit si la liste est vide
bool listeVide(TListePlayer l){
    return (l==NULL);
}

//affiche la liste passée en parametre
void afficheListeV1(TListePlayer l){
    TListePlayer p = l;
    while (p!=NULL){
        print(p->pdata);
        p=p->suiv;
    }
}

//dans cette version "l" est un pointeur sur le pointeur de la 1ere cellule.
void ajoutEnTetePtr2Ptr(TListePlayer *l, Tunite mydata){
    TListePlayer nouv = (TListePlayer)malloc(sizeof(struct T_cell));
    //struct T_cell * nouv = (struct T_cell *)malloc(sizeof(struct T_cell))  //equivalent
    nouv->pdata = (Tunite*)malloc(sizeof(Tunite));
    *(nouv->pdata)=mydata;

    if (*l==NULL) // on cree en fait la premiere cellule de la liste
    {
        nouv->suiv = NULL;
    }
    else  // la liste n'etait pas vide, on doit donc faire les branchements
    {
        nouv->suiv = *l;
    }
    //on modifie l'adresse de la t�te de la liste, soit le contenu point� par l
    *l=nouv;
}

//ajoute une valeur passee en parametre au debut de la liste
TListePlayer ajoutEnTete(TListePlayer l, Tunite mydata){
    TListePlayer nouveau=(TListePlayer)malloc(sizeof(struct T_cell));
    nouveau->pdata=(Tunite*)malloc(sizeof(Tunite));
    *(nouveau->pdata)=mydata;

    if (l==NULL)
    {
        nouveau->suiv=NULL;

    }
    else
    {
        nouveau->suiv=l;
    }
    return nouveau;
}

//ajoute une valeur passe en parametre a la fin de la liste
TListePlayer ajoutEnFin(TListePlayer l, Tunite mydata){
    if(l==NULL)
    {
        l=(TListePlayer)malloc(sizeof(struct T_cell));
        l->pdata=(Tunite *)malloc(sizeof(Tunite));
        l->suiv=NULL;
        *(l->pdata)=mydata;
    }
    else
    {
        TListePlayer res=l;
        TListePlayer nouv=(TListePlayer)malloc(sizeof(struct T_cell));
        nouv->pdata=(Tunite *)malloc(sizeof(Tunite));
        *(nouv->pdata)=mydata;
        nouv->suiv=NULL;
        while(res->suiv!=NULL)
        {
            res=res->suiv;
        }
        res->suiv=nouv;
    }
    return l;
}

//ajoute une valeur a la place N dans la liste
//si cette place est (<=1) alors ajout a la première place
//si la valeur est supérieure à la longueur de la liste alors ajout à la fin
TListePlayer ajoutEnN(TListePlayer l, int pos, Tunite mydata){
    TListePlayer liste=l;
    TListePlayer nouv=(TListePlayer)malloc(sizeof(struct T_cell));
    nouv->pdata=(Tunite *)malloc(sizeof(Tunite));
    *(nouv->pdata)=mydata;

    if(l==NULL)
    {
        l=(TListePlayer)malloc(sizeof(struct T_cell));
        l->pdata=(Tunite *)malloc(sizeof(Tunite));
        l->suiv=NULL;
        *(l->pdata)=mydata;
    }
    else if(pos<=1)
    {
        l=ajoutEnTete(liste, mydata);
    }
    else
    {
      for(int i=2; i<pos; i++){
            liste=liste->suiv;
            if((liste->suiv)==NULL)
            {
                break;
            }
      }
      if((liste->suiv)==NULL)
        {
            ajoutEnFin(liste, mydata);
        }
      else{
      nouv->suiv=(liste->suiv);
      liste->suiv=nouv;
      }
    }
    return l;
}

//supprime la premiere valeur de la liste
TListePlayer suppEnTete(TListePlayer l){
    TListePlayer res;
    initListe(&res);
    res=l->suiv;
    free(l->pdata);
    free(l);
    return res;
}

//supprime la derniere valeur de la liste
TListePlayer suppEnFin(TListePlayer l){
    TListePlayer res=l;
    TListePlayer tmp;
    while(res->suiv!=NULL){
        tmp=res;
        res=res->suiv;
    }
    TListePlayer l2=res;
    tmp->suiv=NULL;
    free(l2->pdata);
    free(l2);
    return l;
}

//supprime la valeur en position N sur la liste
//si la position selectionnee est (<=1) supprime la premiere valeur
//si la position est supperieure a la longueur de la liste, supprime la derniere valeur.
TListePlayer suppEnN(TListePlayer l, int pos){
    TListePlayer res=l;
    TListePlayer tmp;
    if(pos<=1)
    {
        l=suppEnTete(l);
    }
    else
    {
        for(int i=2; i<=pos; i++){
            tmp=res;
            res=res->suiv;
            if((res->suiv)==NULL){
                break;
            }
        }
        if((res->suiv)==NULL){
            l=suppEnFin(l);
        }
        else
        {
            TListePlayer suite=(res->suiv);
            tmp->suiv=suite;
            res->suiv=NULL;
            free(res->pdata);
            free(res);
        }
    }
    return l;
}

//renvoie un pointeur sur la premiere cellule de la liste
TListePlayer getptrFirstCell(TListePlayer l){
    TListePlayer ptr;
    if(l==NULL){
        ptr=NULL;
    }
    else
    {
        ptr=l;
    }
    return ptr;
}

//renvoie un pointeur sur la derniere cellule de la liste
TListePlayer getptrLastCell(TListePlayer l){
    TListePlayer *ptr;
    if(l==NULL){
        ptr=NULL;
    }
    else
    {
        while((l->suiv)!=NULL){
                l=l->suiv;
        }
        ptr=l;
    }
    return ptr;
}

//renvoie un pointeur sur la cellule suivante de la liste
TListePlayer getptrNextCell(TListePlayer l){
    TListePlayer *ptr;
    if(l==NULL){
        ptr=NULL;
    }
    else
    {
        l=l->suiv;
        ptr=l;
    }
    return ptr;
}
/*

NE FONCTIONNE PLUS AVEC UNE LISTE SIMPLE
//renvoie un pointeur sur la cellule precedente de la liste
TListePlayer getptrPrevCell(TListePlayer l){
    if(l==NULL){
        return NULL;
    }
    else
    {
        return l->prec;
    }
}*/

//renvoie un pointeur sur la valeur contenu dans le champ pdata
Tunite* getPtrData(TListePlayer l){
    if(l==NULL){
        return NULL;
    }
    else
    {
        return l->pdata;
    }
}

//echange les valeurs de deux champs pdata
void swapPtrData( TListePlayer source, TListePlayer destination ){
    Tunite *p1=getPtrData(source), *p2=getPtrData(destination);
    Tunite tmp = *p1;
    *p1=*p2;
    *p2=tmp;
}

//donne le nombre de cellules de la liste
int getNbreCell(TListePlayer l){
    int res=0;
    while(l!=NULL){
        l=l->suiv;
        res=res+1;
    }
    return res;
}

//renvoie la taille en bits d'une liste
int getSizeBytes(TListePlayer l){
    int len=getNbreCell(l);
    int res=len*sizeof(struct T_cell);
    return res;
}

//renvoie une nouvelle liste cree a partir de deux liste mise l'une derriere l'autre;
//les liste d'origine ne sont pas modifiees
TListePlayer creatNewListeFromFusion(TListePlayer l1, TListePlayer l2){
    TListePlayer res;
    initListe(&res);
    int len=getNbreCell(l1);
    TListePlayer *p=getptrFirstCell(l1);
    Tunite *ptr=getPtrData(p);
    for(int i=0; i<len; i++){
        res=ajoutEnFin(res,*ptr);
        p=getptrNextCell(p);
        ptr=getPtrData(p);
    }
    len=getNbreCell(l2);
    p=getptrFirstCell(l2);
    ptr=getPtrData(p);
    for(int i=0; i<len; i++){
        res=ajoutEnFin(res,*ptr);
        p=getptrNextCell(p);
        ptr=getPtrData(p);
    }
    return res;
}

//renvoie la premiere liste avec la deuxieme liste rajoutee a la premiere
TListePlayer addBehind(TListePlayer debut, TListePlayer suite){
    TListePlayer *ptr=getptrLastCell(debut);
    (*ptr)->suiv=suite;
    return debut;
}

/*
//trouve la premiere cellule qui contient la valeur data dans son champ pdata
TListePlayer findCell(TListePlayer l, Tunite data, bool (*fcomp)(Tunite a, Tunite b) ){
    TListePlayer *ptr=l;
    TListePlayer res=NULL;
    Tunite *p=getPtrData(ptr);
    while((*fcomp)!=true && (*ptr)->suiv!=NULL){
        ptr=getptrNextCell(ptr);
        p=getPtrData(ptr);
    }
    if(p==data){
        res=ptr;
    }
    else printf("valeur pas dans la liste");
    return res;
}
*/


/*
//renvoie le nombre de fois que data est present dans la liste
int getOccurences(TListePlayer l, int data){
    TListePlayer *ptr=getptrFirstCell(l);
    int *p=getPtrData(ptr);
    int res=0;
    int len=getNbreCell(ptr);
    for(int i=0; i<len; i++){
        if(*p==data){
            res=res+1;
        }
        ptr=getptrNextCell(ptr);
        p=getPtrData(ptr);
    }

    return res;
}
*/

//affiche la liste grace à ??
void afficheListeV2( TListePlayer l){
    Tunite *ptr=getPtrData(l);
    TListePlayer *p=getptrFirstCell(l);
    int len=getNbreCell(l);
    for(int i=0; i<len; i++){
        print(ptr);
        p=getptrNextCell(p);
        ptr=getPtrData(p);
    }
}

//*******************************
//***********etape 2 ************
//*******************************

/*TListePlayer listeNbrAlea(int n){
    TListePlayer l;
    initListe(&l);
    srand(time(NULL));
    for(int i=0; i<n; i++){
        l=ajoutEnFin(l, getPersAlea(i));
    }
    return l;
}
*/

void affichetab(int *tab, int len){
    for(int i=0; i<len; i++){
        printf("%d ", tab[i]);
    }
}


Tunite *converttab(TListePlayer l, int taille){
    TListePlayer *p=getptrFirstCell(l);
    Tunite *val=getPtrData(p);
    Tunite *tab=(Tunite *)malloc(sizeof(Tunite)*taille);
    for(int i=0; i<taille; i++){
        tab[i]=*val;
        p=getptrNextCell(p);
        val=getPtrData(p);
    }
    return tab;
}

Tunite *converttabfree(TListePlayer l, int taille){
    TListePlayer *p=getptrFirstCell(l);
    Tunite *val=getPtrData(p);
    Tunite *tab=(Tunite *)malloc(sizeof(Tunite)*taille);
    for(int i=0; i<taille; i++){
        tab[i]=*val;
        p=getptrNextCell(p);
        val=getPtrData(p);
    }
    p=getptrFirstCell(l);
    val=getPtrData(p);
    TListePlayer *p2=getptrNextCell(p);
    Tunite *val2=getPtrData(p2);
    for(int i=0; i<taille; i++){
        free(val);
        free(p);
        p=p2;
        val=val2;
        p2=getptrNextCell(p);
        val2=getPtrData(p2);
    }
    return tab;
}

TListePlayer tabintoliste(Tunite *tab, int len){
    TListePlayer res;
    initListe(&res);
    for(int i=0; i<len; i++){
        Tunite val=tab[i];
        res=ajoutEnFin(res, val);
    }
    return res;
}

/*
void tri_selection(Tunite *tableau, int taille)
{

    int en_cours, plus_petit, j, temp;

    for(en_cours = 0; en_cours<taille-1; en_cours++)
    {
        plus_petit=en_cours;
        for(j = en_cours; j<taille; j++)
            if(tableau[j]<tableau[plus_petit])
                plus_petit=j;
        temp=tableau[en_cours];
        tableau[en_cours]=tableau[plus_petit];
        tableau[plus_petit]=temp;
    }
}

TListePlayer triliste(TListePlayer l){
    int len=getNbreCell(l);
    Tunite *tab=(Tunite *)malloc(sizeof(Tunite)*len);
    tab=converttab(l, len);
    tri_selection(tab, len);
    TListePlayer res=tabintoliste(tab, len);
    return res;
}
*/

//**************************************************
//******************Etape 3*************************
//**************************************************

/*
void tri_selection_liste(TListePlayer l)
{
    int *petit, *grand;

    for(TListePlayer p=l; (p->suiv)!=NULL; p=p->suiv)
    {
        petit=getPtrData(p);
        for(TListePlayer j=l; (j->suiv)!=NULL; j=j->suiv)
        {
            grand=getPtrData(j);
            if((*grand)>(*petit))
                swapPtrData(p,j);
        }

    }
    TListePlayer *ptr=getptrFirstCell(l);
    petit=getPtrData(getptrLastCell(l));
    int len=getNbreCell(l);
    grand=getPtrData(ptr);
    for(int i=0; i<len; i++){
        if(*petit<=*grand){
            ajoutEnN(l, i+1, *petit);
            suppEnFin(l);
            break;
        }
        ptr=getptrNextCell(ptr);
        grand=getPtrData(ptr);

    }


}
*/

//**************************************************
//******************Etape 4*************************
//**************************************************

/*TListePlayer creerListeNElem(int taille){
    TListePlayer res;
    for(int i=0; i<taille; i++){
        res=ajoutEnFin(res,getPersAlea(i));
    }
    return res;
}*/


void print (Tunite *p){
    printf("PV: %d, nom: %f, degats: %d, portee: %d, vitesse: %f, posX: %d, posY: %d, attaque: %d\n",
           p->pointsDeVie, p->vitesseAttaque, p->degats, p->portee, p->vitessedeplacement, p->posX, p->posY, p->peutAttaquer );
};


