#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "SDL.h"
#include "maSDL.h"
#include "towerdefend.h"
#include "listeDouble.h"
#include <math.h>
#include <time.h>

//typedef Tunite* ** TplateauJeu;

TplateauJeu AlloueTab2D(int largeur, int hauteur){
    TplateauJeu jeu;
    jeu = (Tunite***)malloc(sizeof(Tunite**)*largeur);
    for (int i=0;i<largeur;i++){
        jeu[i] = (Tunite**)malloc(sizeof(Tunite*)*hauteur);
    }
    return jeu;  //tab2D contenant des pointeurs
}
void initPlateauAvecNULL(TplateauJeu jeu,int largeur, int hauteur){
    for (int i=0;i<largeur;i++){
        for (int j=0;j<hauteur;j++){
            jeu[i][j] = NULL;
        }
    }
/*
    //POUR LA DEMO D'AFFICHAGE UNIQUEMENT, A SUPPRIMER
    //(les tours ici ne sont pas liées aux listes des unités de vos joueurs)
    jeu[5][3]=creeTourSol(5,3);
    jeu[3][3]=creeTourAir(3,3);
    jeu[4][1]=creeTourRoi(4,1);
    jeu[4][15]=creeTourAir(4,15);
    jeu[5][17]=creeDragon(5,17);
    //FIN DEMO AFFICHAGE
    */
}


/*
void ecritCheminVersleHaut  : permet d'initilaiser le tab chemin de distance cases (avec des coord x y) dans une direction, à partir d'un point x y donné

int **chemin  : tab de coordonnées x y du chemin
int *ichemin  : indice de la case du chemin d'où on part
int *xdepart, int *ydepart : valeur en x y de départ pouri la premiere case
int distance  : distance sur laquelle on va écrire des coordonnées dans le tab chemin
int *distanceMaxRestante : securité pour ne pas sortir de la plage d'indice de chemin
*/
void ecritCheminVersleHaut(int **chemin, int *ichemin, int *xdepart, int *ydepart, int distance, int *distanceMaxRestante){
    if ((*distanceMaxRestante - distance)>=0){
        int y;
        for (y=*ydepart;y>*ydepart-distance;y--){
            chemin[*ichemin][X]= *xdepart;
            chemin[*ichemin][Y]= y;
            (*ichemin)++;
        }
        *ydepart=y;
    }
    else printf("erreur longueur chemin\n");
}
void ecritCheminVerslaDroite(int **chemin, int *ichemin, int *xdepart, int *ydepart, int distance, int *distanceMaxRestante){
    if ((*distanceMaxRestante - distance)>=0){
        int x;
        for (x=*xdepart;x<*xdepart+distance;x++){
            chemin[*ichemin][X]= x;
            chemin[*ichemin][Y]= *ydepart;
            (*ichemin)++;
        }
        *xdepart=x;
    }
    else printf("erreur longueur chemin\n");
}
void ecritCheminVerslaGauche(int **chemin, int *ichemin, int *xdepart, int *ydepart, int distance, int *distanceMaxRestante){
    if ((*distanceMaxRestante - distance)>=0){
        int x;
        for (x=*xdepart;x>*xdepart-distance;x--){
            chemin[*ichemin][X]= x;
            chemin[*ichemin][Y]= *ydepart;
            (*ichemin)++;
        }
        *xdepart=x;
    }
    else printf("erreur longueur chemin\n");
}

int **initChemin(){
    int **chemin = (int**)malloc(sizeof(int*)*NBCOORDPARCOURS);

    for (int j=0;j<NBCOORDPARCOURS;j++){
        chemin[j] = (int*)malloc(sizeof(int)*2);  //2 cases :indice 0 pour la coord en X, indice 1 pour la coord en Y
    }

    int ydepart = 18;  //19 on part du bas
    int xdepart = 5;  //5 = milieu de la fenetre de 11 de largeur (0-10)
    int i = 0;  //parcourt les i cases du chemin
    int distanceMaxRestante = NBCOORDPARCOURS;

    ecritCheminVersleHaut(chemin, &i, &xdepart, &ydepart, 3, &distanceMaxRestante);
    ecritCheminVerslaDroite(chemin, &i, &xdepart, &ydepart, 4, &distanceMaxRestante);
    ecritCheminVersleHaut(chemin, &i, &xdepart, &ydepart, 4, &distanceMaxRestante);
    ecritCheminVerslaGauche(chemin, &i, &xdepart, &ydepart, 5, &distanceMaxRestante);
    ecritCheminVersleHaut(chemin, &i, &xdepart, &ydepart, 4, &distanceMaxRestante);
    ecritCheminVerslaDroite(chemin, &i, &xdepart, &ydepart, 4, &distanceMaxRestante);
    ecritCheminVersleHaut(chemin, &i, &xdepart, &ydepart, 3, &distanceMaxRestante);
    ecritCheminVerslaGauche(chemin, &i, &xdepart, &ydepart, 4, &distanceMaxRestante);
    ecritCheminVersleHaut(chemin, &i, &xdepart, &ydepart, 3, &distanceMaxRestante);

    return chemin;  //tab2D contenant des pointeurs
}


void afficheCoordonneesParcours(int **chemin, int nbcoord){
    printf("Liste coordonnees: ");
    for (int i=0; i<nbcoord; i++){
        printf("(%d, %d)",chemin[i][X], chemin[i][Y]);
    }
    printf("\nfin liste coordonnées\n");
}


void affichePlateauConsole(TplateauJeu jeu, int largeur, int hauteur){
    //pour un affichage sur la console, en relation avec enum TuniteDuJeu
    const char* InitialeUnite[7]={"s", "a", "r", "A", "C", "D", "G"};

    printf("\n");
    for (int j=0;j<hauteur;j++){
        for (int i=0;i<largeur;i++){
                // A ne pas donner aux etudiants
            if (jeu[i][j] != NULL){
                    printf("%s",InitialeUnite[jeu[i][j]->nom]);
            }
            else printf(" ");  //cad pas d'unité sur cette case
        }
        printf("\n");
    }
}

Tunite *creeTourSol(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = tourSol;
    nouv->cibleAttaquable = sol;
    nouv->maposition = sol;
    nouv->pointsDeVie = 500;
    nouv->vitesseAttaque = 1.5;
    nouv->degats = 120;
    nouv->portee = 5;
    nouv->vitessedeplacement = 0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    //nouv->cible = NULL;
    return nouv;
}
Tunite *creeTourAir(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = tourAir;
    nouv->cibleAttaquable = air;
    nouv->maposition = sol;
    nouv->pointsDeVie = 500;
    nouv->vitesseAttaque = 1.0;
    nouv->degats = 100;
    nouv->portee = 3;
    nouv->vitessedeplacement = 0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    //nouv->cible = NULL;
    return nouv;
}
Tunite *creeTourRoi(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = tourRoi;
    nouv->cibleAttaquable = solEtAir;
    nouv->maposition = sol;
    nouv->pointsDeVie = 800;
    nouv->vitesseAttaque = 1.2;
    nouv->degats = 180;
    nouv->portee = 4;
    nouv->vitessedeplacement = 0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    //nouv->cible = NULL;
    return nouv;
}


bool tourRoiDetruite(TListePlayer player){
    bool res=(bool)malloc(sizeof(bool));
    if (player->pdata->pointsDeVie==0){
        res=true;
    } else {
        res=false;
    }
    return res;
}


int getposX(Tunite *Unite){
    return (Unite->posX);
}

int getposY(Tunite *Unite){
    return (Unite->posY);
}

TListePlayer quiEstAPorteeHorde(Tunite *UniteAttaquante, Tunite *roi){
    TListePlayer res=NULL;
    int range=UniteAttaquante->portee;
    int rx=roi->posX, ry=roi->posY; //position du roi
    int px=UniteAttaquante->posX, py=UniteAttaquante->posY; //position de l'unité attaquante
    if(px>rx-range && px<rx+range){
        if(py>ry-range && py<ry+range){
           res=ajoutEnTete(res, *roi);
        }
    }
    return res;
}

TListePlayer quiEstAporteeTour(Tunite *UniteAttaquante, TListePlayer horde){
    TListePlayer res=NULL;
    int range=UniteAttaquante->portee;
    int px=getposX(UniteAttaquante), py=getposY(UniteAttaquante);
    int hx, hy;
    int len=getNbreCell(horde);
    for(int i=0; i<len; i++){
        hx=getposX(horde->pdata);
        hy=getposY(horde->pdata);
        if(hx>px-range && hx< px+range){
            if(hy>py-range && hy< py+range){
                if(i==0){
                    res=ajoutEnTete(res, *(horde->pdata));
                }
                else if(UniteAttaquante->cibleAttaquable==horde->pdata->maposition || UniteAttaquante->cibleAttaquable==2){
                    res=ajoutEnTete(res, *(horde->pdata));
                }
            }
        }
        horde=horde->suiv;
    }
    return res;
}

void phase1combat(SDL_Surface *surface, TListePlayer roi, TListePlayer horde, TplateauJeu jeu){ 
    int len=getNbreCell(roi);
    for(int i=0; i<len; i++){
        TListePlayer cible=NULL;
        Tunite *r=roi->pdata;
        cible=quiEstAporteeTour(roi->pdata, horde); //On attaque seulement ce qui est à bonne porté
        if(cible!=NULL){
            cible=triPV(cible); 
            Tunite *uniteattaquee=cible->pdata;
            if(roi->pdata->peutAttaquer==1){
                combat(surface, r, uniteattaquee, horde, jeu);

            }
        }
        roi=roi->suiv;
    }
}

void phase2combat(SDL_Surface *surface, TListePlayer roi, TListePlayer horde, TplateauJeu jeu){
    int len=getNbreCell(horde);
    for(int i=0; i<len; i++){
        TListePlayer cible=NULL;
        Tunite *h=horde->pdata;
        cible=quiEstAPorteeHorde(horde->pdata, roi->pdata);
        if(cible!=NULL){
            Tunite *uniteattaquee=cible->pdata;
            if(horde->pdata->peutAttaquer==1){
                combat(surface, h, uniteattaquee, roi, jeu);
            }
        }
        horde=horde->suiv;
    }
}

void combat(SDL_Surface *surface , Tunite *UniteAttaquante, Tunite *UniteCible, TListePlayer player, TplateauJeu jeu){ //ajout de player en paramètre pour supprimer directement l'unité
    int dgts=UniteAttaquante->degats;
    dgts=((UniteCible->pointsDeVie)-dgts);//On prend les point de vie de l'unité cible et on lui soustrait les degats causés par l'unité attaquante
    player->pdata->pointsDeVie=dgts;

    dessineAttaque(surface, UniteAttaquante, UniteCible);
}

Tunite *creeArcher(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = archer;
    nouv->cibleAttaquable = solEtAir;
    nouv->maposition = sol;
    nouv->pointsDeVie = 80;
    nouv->vitesseAttaque = 0.7;
    nouv->degats = 120;
    nouv->portee = 3;
    nouv->vitessedeplacement = 1.0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    return nouv;
}



Tunite *creeGargouille(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = gargouille;
    nouv->cibleAttaquable = solEtAir;
    nouv->maposition = air;
    nouv->pointsDeVie = 80;
    nouv->vitesseAttaque = 0.6;
    nouv->degats = 90;
    nouv->portee = 1;
    nouv->vitessedeplacement = 3.0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    return nouv;
}


Tunite *creeDragon(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = dragon;
    nouv->cibleAttaquable = solEtAir;
    nouv->maposition = air;
    nouv->pointsDeVie = 200;
    nouv->vitesseAttaque = 1.1;
    nouv->degats = 180;
    nouv->portee = 2;
    nouv->vitessedeplacement = 2;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    return nouv;
}

Tunite *creeChevalier(int posx, int posy){
    Tunite *nouv = (Tunite*)malloc(sizeof(Tunite));
    nouv->nom = chevalier;
    nouv->cibleAttaquable = sol;
    nouv->maposition = sol;
    nouv->pointsDeVie = 400;
    nouv->vitesseAttaque = 1.5;
    nouv->degats = 250;
    nouv->portee = 1;
    nouv->vitessedeplacement = 2.0;
    nouv->posX = posx;
    nouv->posY = posy;
    nouv->peutAttaquer = 1;
    return nouv;
}

TListePlayer supprimerUnite(TListePlayer player, Tunite *UniteDetruite, TplateauJeu jeu){
    TListePlayer res=NULL;
    if (player==NULL){
        return res;
    }
    TListePlayer previous=NULL;
    TListePlayer current=player;
    while(current!=NULL && current->pdata->pointsDeVie>0){
        previous=current;
        current=current->suiv;
    }
    if(current==NULL){
        return player;
    }
    if(previous==NULL){
        res=current->suiv;
    }
    else {
            previous->suiv=current->suiv;
            res=previous;
    }
    printf("l'unite %d(%d,%d) est supprimee: %d pvs\n",current->pdata->nom,current->pdata->posX,current->pdata->posY,current->pdata->pointsDeVie);
    jeu[current->pdata->posX][current->pdata->posY] = NULL;
    free(current->pdata);
    free(current);
    return res;
}


void AjouterUnite(TListePlayer player, Tunite *nouvelleUnite){
    player=ajoutEnFin(player, *nouvelleUnite);
}



TListePlayer triPV(TListePlayer player){ //On se sert de cette fonction afin d'attaquer l'unité avec le moions de points de vie,
                                         //pour ça on a besoin d'une liste trié dans l'odre croissant.
    for(TListePlayer l=player; l!=NULL; l=l->suiv){
        for(TListePlayer l2=player->suiv; l2!=NULL; l2=l2->suiv){
            if((l->pdata->pointsDeVie)>(l2->pdata->pointsDeVie)){
                swapPtrData(l, l2);
            }

        }
    }
    return player;
}


void PositionnePlayerOnPlateau(TListePlayer player, TplateauJeu jeu){
    while(player!=NULL){
        int x=getposX(getPtrData(player));
        int y=getposY(getPtrData(player));
        jeu[x][y]=player->pdata;
        player=player->suiv;
    }
}


int getindcasechemin(TListePlayer player, int** chemin){ //Fonction utilitaire
    int x=getposX(getPtrData(player));
    int y=getposY(getPtrData(player));
    int res;
    int i;
    for(i=0; i<NBCOORDPARCOURS; i++){
        if(x==chemin[i][0] && y==chemin[i][1])
            res=i;
    }
    return res;
}


void moveUnite(TListePlayer player, TplateauJeu jeu, int ** chemin){
    int len=getNbreCell(player);
    int tmpx, tmpy;
    float vit;
    for(int l=0; l<len; l++){
        tmpx=getposX(player->pdata);
        tmpy=getposY(player->pdata);
        vit=player->pdata->vitessedeplacement;
        int i=vit;
        int ind=getindcasechemin(player, chemin);
        for(i=vit; i>0; i--){  //On décrémente car si jamais la case ou l'unité doit aller est prise, l'unité avancera sur la case situé avant celle ci
            ind=ind+i;
            if(ind>NBCOORDPARCOURS){
                break;
            } else {
                int x=chemin[ind][0];
                int y=chemin[ind][1];
                if(jeu[x][y]==NULL){
                    player->pdata->posX=x;
                    player->pdata->posY=y;
                    jeu[x][y]=player->pdata;
                    jeu[tmpx][tmpy]=NULL;
                    break;

                }
            }
        }
        player=player->suiv;
    }
}



//permet de prendre une unité au hasard parmi les 4 possibles pour la horde
Tunite *randomunitehorde(int posx, int posy){
    Tunite *nouv;
    srand(time(NULL));
    int x=rand() %4;
    if(x==0){
        nouv=creeArcher(posx, posy);
    }
    else if(x==1){
        nouv=creeChevalier(posx, posy);
    }
    else if(x==2){
        nouv=creeDragon(posx, posy);
    }
    else if(x==3) nouv=creeGargouille(posx, posy);
    return nouv;
}


bool creationpossible(int Min, int Max){ //Pourcentage min et max défini pour chaque unité
    srand(time(NULL));
    int nouvmax= Min + rand() % Max-Min; //Tire aléatoirement le pourcentage d'apparition
    int prob=rand() % 100;
    return (prob<=nouvmax);
}

Tunite *randomtour(int x, int y){ //Choisit entre une tour de type air ou de type sol
    Tunite *tour=NULL;
    srand(time(NULL));
    int rdm=rand()% 2;
    if(rdm==0){
        tour=creeTourAir(x,y);
    }
    else if(rdm==1){
        tour=creeTourSol(x, y);
    }
    return tour;
}

void creerrandomtour(TListePlayer roi, TplateauJeu jeu, int** chemin){
    int xmin=2, xmax=10;
    int ymin=3, ymax=18;
    srand(time(NULL));
    int x;
    int y;
    x=xmin+rand()%xmax-xmin;
    y=ymin+rand()%ymax-ymin;
    bool ok=true;
    for(int i=0; i<NBCOORDPARCOURS; i++){
        if(x==chemin[i][0]&&y==chemin[i][1]){
            ok=false;
        }
    }
    if(ok==true){
        AjouterUnite(roi, randomtour(x, y));
    } else {
        printf("creation de tour annulee coordonee fausses");
    }
}

void sauvegardeseq(char *sortie, TListePlayer roi, TListePlayer horde){
    FILE * f_out;
    int lenroi=getNbreCell(roi);
    int lenhorde=getNbreCell(horde);
    int nb=lenhorde+lenroi;

    if ((f_out = fopen(sortie,"w")) == NULL){
        fprintf(stderr, "\nErreur: Impossible d'ecrire dans le fichier %s\n",sortie);
        return(EXIT_FAILURE);
    }
    f_out=fopen(sortie, "w");
    fprintf(f_out, "%d\n", nb);

    for(int i=0; i<nb; i++){
        for(int ii=0; ii<lenroi; ii++){
            fprintf(stdout, "%d %d %d %d %f %d %d %f %d %d %d \n", roi->pdata->nom, roi->pdata->cibleAttaquable, roi->pdata->maposition, roi->pdata->pointsDeVie, roi->pdata->vitesseAttaque, roi->pdata->degats, roi->pdata->portee, roi->pdata->vitessedeplacement, roi->pdata->posX, roi->pdata->posY, roi->pdata->peutAttaquer);
            roi=roi->suiv;
        }
    }
    for(int i=0; i<nb; i++){
        for(int ii=0; ii<lenhorde; ii++){
            fprintf(stdout, "%d %d %d %d %f %d %d %f %d %d %d \n", horde->pdata->nom, horde->pdata->cibleAttaquable, horde->pdata->maposition, horde->pdata->pointsDeVie, horde->pdata->vitesseAttaque, horde->pdata->degats, horde->pdata->portee, horde->pdata->vitessedeplacement, horde->pdata->posX, horde->pdata->posY, horde->pdata->peutAttaquer);
            horde=horde->suiv;
        }
    }
    fclose(f_out);

}

void readseq(char *entree, TListePlayer roi, TListePlayer horde){
    FILE * f_in;
    f_in=fopen(entree,"r");
    int c;
    int nbint;
    if ((f_in = fopen(entree,"r")) == NULL){
        fprintf(stderr, "\nErreur: Impossible de lire le fichier %s\n",entree);
        return(EXIT_FAILURE);
    }
    fscanf(f_in,"%d",&nbint);
    for (int i=0;i<nbint;i++){
        fscanf(f_in,"%d",&c);
        printf("%d = %d\n",i+1,c);
    }
    printf("Le fichier sortie.txt a lu\n");
    fclose(f_in);
}

void sauvegardebin(char *sortie, TListePlayer roi, TListePlayer horde){
    FILE * f_out;
    int lenroi=getNbreCell(roi);
    int lenhorde=getNbreCell(horde);
    int nb=lenhorde+lenroi;
    if ((f_out = fopen(sortie,"wb")) == NULL){
        fprintf(stderr, "\nErreur: Impossible d'ecrire dans le fichier %s\n",sortie);
        return(EXIT_FAILURE);
    }
    f_out=fopen(sortie, "w");
    fwrite(&nb,sizeof(int), 1,f_out);

    for(int i=0; i<nb; i++){
        for(int ii=0; ii<lenroi; ii++){
            fwrite(roi->pdata, sizeof(int), sizeof(Tunite), f_out);
            roi=roi->suiv;
        }
    }
    for(int i=0; i<nb; i++){
        for(int ii=0; ii<lenhorde; ii++){
            fwrite(horde->pdata, sizeof(int), sizeof(Tunite), f_out);
            horde=horde->suiv;
        }
    }
    fclose(f_out);
}

void readbin(char *entree, TListePlayer roi, TListePlayer horde){
    FILE * f_in;
    f_in=fopen(entree,"r");
    int c;
    int nbint;
    if ((f_in = fopen(entree,"rb")) == NULL){
        fprintf(stderr, "\nErreur: Impossible de lire le fichier %s\n",entree);
        return(EXIT_FAILURE);
    }
    fread(&nbint, sizeof(int),1,f_in);
    for (int i=0;i<nbint;i++){
        fread(&c, sizeof(int),1,f_in);
        printf("entier %d = %d\n",i+1,c);
    }
    printf("Le fichier sortie.txt a lu\n");
    fclose(f_in);
    return(EXIT_SUCCESS);

}
