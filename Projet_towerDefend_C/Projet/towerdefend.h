#ifndef TOWERDEFEND_H_INCLUDED
#define TOWERDEFEND_H_INCLUDED
#include "SDL.h"
#include <stdbool.h>
#define LARGEURJEU 11
#define HAUTEURJEU 19
#define NBCOORDPARCOURS 34
#define X 0
#define Y 1

typedef enum{tourSol, tourAir, tourRoi, archer, chevalier, dragon, gargouille} TuniteDuJeu;
typedef enum{sol, solEtAir, air } Tcible;


typedef struct {
    TuniteDuJeu nom;
    Tcible cibleAttaquable;	//indique la position des unités que l’on peut attaquer
    Tcible maposition;		//indique soit « air » soit « sol », utile pour savoir
                            //qui peut nous attaquer
    int pointsDeVie;
    float vitesseAttaque;	//en seconde, plus c’est petit plus c’est rapide
    int degats;
    int portee ;			//en mètre, distance sur laquelle on peut atteindre une
                            //cible

    float vitessedeplacement;	//en m/s
    int posX, posY;			//position sur le plateau de jeu
    int peutAttaquer;		//permet de gérer le fait que chaque unité attaque une
                            //seule fois par tour ;
                            //0 = a déjà attaqué, 1 = peut attaquer ce tour-ci
                            // à remettre à 1 au début de chaque tour

    //struct Tunite *cible;   //NULL si pas de cible. mettre à jour ce pointeur permet l'aninamtion (simpliste certe) du tir
    //non utilisé au final -> utiliser directement dessineAttaque
} Tunite;

typedef struct T_cell{
    struct T_cell *suiv;
    Tunite *pdata; //pointeur vers une unité
} *TListePlayer;


typedef Tunite* ** TplateauJeu;  ////tableau a deux dimensions de largeur 11 et hauteur 19 contenant des pointeurs (Tunite*)




TplateauJeu AlloueTab2D(int largeur, int hauteur);
void afficheCoordonneesParcours(int **t, int nbcoord);
int **initChemin();         //retourne le chemin emprunter par la horde, sous forme d'un tableau[NBCOORDPARCOURS][2]

void initPlateauAvecNULL(TplateauJeu jeu,int largeur, int hauteur);
void affichePlateauConsole(TplateauJeu jeu, int largeur, int hauteur);

Tunite *creeTourSol(int posx, int posy);
Tunite *creeTourAir(int posx, int posy);
Tunite *creeTourRoi(int posx, int posy);


bool tourRoiDetruite(TListePlayer player);
void PositionnePlayerOnPlateau(TListePlayer player, TplateauJeu jeu);
int getposX(Tunite *Unite);
int getposY(Tunite *Unite);

TListePlayer quiEstAPorteeHorde(Tunite *UniteAttaquante, Tunite *roi) ; //retourne la liste des cibles possibles
TListePlayer quiEstAporteeTour(Tunite *UniteAttaquante, TListePlayer horde);

void phase1combat(SDL_Surface *surface, TListePlayer roi, TListePlayer horde, TplateauJeu jeu);
void phase2combat(SDL_Surface *surface, TListePlayer roi, TListePlayer horde, TplateauJeu jeu);


TListePlayer triPV(TListePlayer player);
void combat(SDL_Surface *surface , Tunite * UniteAttaquante, Tunite * UniteCible, TListePlayer player, TplateauJeu jeu) ;


Tunite *creeArcher(int posx, int posy);
Tunite *creeGargouille(int posx, int posy);

Tunite *creeDragon(int posx, int posy);

Tunite *creeChevalier(int posx, int posy);

TListePlayer supprimerUnite(TListePlayer player, Tunite *UniteDetruite, TplateauJeu jeu);
//Tunite AcheteUnite(int *elixirEnStockduJoueur);
void AjouterUnite(TListePlayer player, Tunite *nouvelleUnite);

int getindcasechemin(TListePlayer player, int** chemin);
void moveUnite(TListePlayer player, TplateauJeu jeu, int ** chemin);


Tunite *randomunitehorde(int posx, int posy);
bool creationpossible(int Min, int Max);
Tunite *randomtour(int x, int y);
void creerrandomtour(TListePlayer roi, TplateauJeu jeu, int ** chemin);


void readbin(char *entree, TListePlayer roi, TListePlayer horde);
void sauvegardebin(char *sortie, TListePlayer roi, TListePlayer horde);
void readseq(char *entree, TListePlayer roi, TListePlayer horde);
void sauvegardeseq(char *sortie, TListePlayer roi, TListePlayer horde);


#endif // TOWERDEFEND_H_INCLUDED
