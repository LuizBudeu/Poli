/*
 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__

  AO PREENCHER ESSE CABE�ALHO COM O MEU NOME E O MEU N�MERO USP,
  DECLARO QUE SOU O �NICO AUTOR E RESPONS�VEL POR ESSE PROGRAMA.
  TODAS AS PARTES ORIGINAIS DESSE EXERC�CIO-PROGRAMA (EP) FORAM
  DESENVOLVIDAS E IMPLEMENTADAS POR MIM SEGUINDO AS INSTRU��ES DESSE EP
  E QUE PORTANTO N�O CONSTITUEM PL�GIO. DECLARO TAMB�M QUE SOU RESPONS�VEL
  POR TODAS AS C�PIAS DESSE PROGRAMA E QUE EU N�O DISTRIBUI OU FACILITEI A
  SUA DISTRIBUI��O. ESTOU CIENTE QUE OS CASOS DE PL�GIO S�O PUNIDOS COM
  REPROVA��O DIRETA NA DISCIPLINA.

  Nome: Luiz Guilherme Budeu
  NUSP: 11821639
  Engenharia de Computa��o
  02/10/2020
  Cristina Gomes
  EP2

  main.c

  Refer�ncias: Com exce��o das rotinas fornecidas no esqueleto e em sala
  de aula, caso voc� tenha utilizado alguma refer�ncia, liste-as abaixo
  para que o seu programa n�o seja considerada pl�gio.

 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__
*/


#include <stdio.h>
#include <stdlib.h>
#include "polinomios.h"


/* declaracao da celula das listas encadeadas */
struct poli {
    double coeficiente;
    int expoente;
    struct poli *prox;
};
typedef struct poli Polinomio;

/* prototipo de eventuais funcoes auxiliares */
void *mallocSafe (int nbytes);
polinomio alocaMonomio();
void tiraUltimo (Polinomio *p);
void decrescente (Polinomio *p);

/* implementacao das funcoes da biblioteca polinomios.h */
polinomio cria() {
    return NULL;
}

polinomio leia() {
  int expo, nao_zero = 1;
  double coef;
  Polinomio *ini, *p;

  /* Primeiro lemos os dados e os guardamos na lista encadeada, come�ada por 'ini' */
  p = alocaMonomio();
  ini = p;

  while (nao_zero){
    scanf("%lf", &coef);
    if (coef != 0){
      scanf("%d", &expo);
      p->coeficiente = coef;
      p->expoente = expo;
      p->prox = alocaMonomio();
      p = p->prox;
    }
    else
      nao_zero = 0;
  }

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(ini);

  /* Mas como fizemos a lista n�o necessariamente numa ordem decrescente, faremos exatamente isso */
  decrescente(ini);

  return ini;
}

polinomio copia(polinomio p) {
  Polinomio *r, *inir, *q;

  r = alocaMonomio();
  inir = r;

  /* Primeiro copiamos o polin�mio p para o polin�mio r */
  for(q = p; q != NULL; q = q->prox){
    r->coeficiente = q->coeficiente;
    r->expoente = q->expoente;
    r->prox = alocaMonomio();
    r = r->prox;
  }
  r->prox = NULL;

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(inir);

  return inir;
}

void impr(char c, polinomio p) {
  Polinomio *q;

  q = p;

  printf("%c(x) = ", c);
  if(q == NULL)
    printf("0\n");

  else{
      for (q = p; q != NULL; q = q->prox){
        if(q->expoente != 0){
            if(q->prox != NULL)
                printf("%.2f x^%d + ", q->coeficiente, q->expoente);
            else
                printf("%.2f x^%d", q->coeficiente, q->expoente);
        }

        else if(q->expoente == 0){
            if(q->prox != NULL)
                printf("%.2f + ", q->coeficiente);
            else
                printf("%.2f", q->coeficiente);
        }
      }
  printf("\n");
  }
}

polinomio soma(polinomio p, polinomio q) {
  Polinomio *r, *inir, *i, *j, *prev, *tira;

  i = p;
  j = q;
  r = alocaMonomio();
  inir = r;

  /* Primeiro percorremos os dois polin�mios, somando-os */
  while(i != NULL && j != NULL){
    if(i->expoente == j->expoente){
        if(i->coeficiente + j->coeficiente != 0){
            r->coeficiente = i->coeficiente + j->coeficiente;
            r->expoente = i->expoente;
            r->prox = alocaMonomio();
            r = r->prox;
        }

            i = i->prox;
            j = j->prox;
    }
    else if(i->expoente > j->expoente){
        r->coeficiente = i->coeficiente;
        r->expoente = i->expoente;
        r->prox = alocaMonomio();
        r = r->prox;

        i = i->prox;
    }
    else{
        r->coeficiente = j->coeficiente;
        r->expoente = j->expoente;
        r->prox = alocaMonomio();
        r = r->prox;

        j = j->prox;
    }
  }

  /* Depois caso ainda sobre elementos em algum polin�mio, colocamo-nos no final do polin�mio r */
  while(i != NULL){
    r->coeficiente = i->coeficiente;
    r->expoente = i->expoente;
    r->prox = alocaMonomio();
    r = r->prox;

    i = i->prox;
  }

  while(j != NULL){
    r->coeficiente = j->coeficiente;
    r->expoente = j->expoente;
    r->prox = alocaMonomio();
    r = r->prox;

    j = j->prox;
  }

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(inir);

  return inir;
}

polinomio subt(polinomio p, polinomio q) {
  /* Como fazer 'x - y' � igual a fazer 'x + (-1)y', � poss�vel utilizar as fun��es 'nega' e 'soma' para realizar a subtra��o */
  return soma(p, nega(q));
}

polinomio nega(polinomio p) {
  Polinomio *n, *inin, *q;

  n = alocaMonomio();
  inin = n;

  /* Primeiro copiamos o polin�mio p para o polin�mio n, com o coeficiente negado */
  for(q = p; q != NULL; q = q->prox){
    n->coeficiente = -(q->coeficiente);
    n->expoente = q->expoente;
    n->prox = alocaMonomio();
    n = n->prox;
  }

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(inin);

  return inin;
}

polinomio mult(polinomio p, polinomio q) {
  Polinomio *i, *j, *r, *inir, *remove, *inii;

  i = p;
  j = q;
  r = alocaMonomio();
  inir = r;

  while(i != NULL){
    while(j != NULL){
        r->coeficiente = i->coeficiente * j->coeficiente;
        r->expoente = i->expoente + j->expoente;
        r->prox = alocaMonomio();
        r = r->prox;

        j = j->prox;
    }

    j = q;
    i = i->prox;
  }

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(inir);

  /* Agora pode ser que haja alguns valores de mesmo expoente que podem ser somados */
  i = inir;
  inii = i;
  j = i->prox;

  while(i != NULL && i->prox != NULL){
    j = i;
    while(j->prox != NULL){
        if(i->expoente == j->prox->expoente){
            i->coeficiente = i->coeficiente + j->prox->coeficiente;
            remove = j->prox;
            j->prox = j->prox->prox;
            free(remove);
        }
        else{
            j = j->prox;
        }
    }
    i = i->prox;
  }

  return inii;
}

polinomio quoc(polinomio p, polinomio q) {
  Polinomio *r, *inir, *dividendo, *divisor, *temp;

  dividendo = p;
  divisor = q;
  r = alocaMonomio();
  inir = r;
  temp = alocaMonomio();

  while(divisor->expoente <= dividendo->expoente){
    r->expoente = dividendo->expoente - divisor->expoente;
    r->coeficiente = dividendo->coeficiente / divisor->coeficiente;
    temp->expoente = r->expoente;
    temp->coeficiente = r->coeficiente;
    temp->prox = NULL;

    dividendo = subt(dividendo, mult(temp, divisor));

    r->prox = alocaMonomio();
    r = r->prox;
  }

  /* Como o m�todo acima deixa um polin�mio nulo a mais como �ltimo elemento, removemos esse �ltimo elemento */
  tiraUltimo(inir);

  return inir;
}

polinomio rest(polinomio p, polinomio q) {
  Polinomio *dividendo, *divisor, *temp;

  dividendo = p;
  divisor = q;
  temp = alocaMonomio();

  while(divisor->expoente <= dividendo->expoente){
    temp->expoente = dividendo->expoente - divisor->expoente;
    temp->coeficiente = dividendo->coeficiente / divisor->coeficiente;

    dividendo = subt(dividendo, mult(temp, divisor));
  }

  return dividendo;
}

void libera(polinomio p) {
  Polinomio *t, *q;

  q = p;

  while(q != NULL){
    t = q;
    q = q->prox;
    free(t);
    t = NULL;
  }

}

/* Implementacao das funcoes auxiliares */
void *mallocSafe (int nbytes) {
    void *ptr;
    ptr = malloc(nbytes);
    if (ptr == NULL) {
        printf("Socorro! malloc devolveu NULL!\n");
        exit(EXIT_FAILURE);
    }
    return ptr;
}

/* Devolve um mon�mio novo */
polinomio alocaMonomio() {
    Polinomio *p;

    p = mallocSafe(sizeof(Polinomio));
    p->coeficiente = 0;
    p->expoente = 0;
    p->prox = NULL;

    return p;
}

/* Retira o �ltimo elemento de uma lista encadeada */
void tiraUltimo (Polinomio *p){
    Polinomio *remove;

    remove = p;
    while(remove->prox->prox != NULL)
        remove = remove->prox;

    free(remove->prox);
    remove->prox = NULL;
}

/* Ordena decrescentemente uma lista encadeada */
void decrescente (Polinomio *p){
    Polinomio *i, *j;
    int aux_expo;
    double aux_coef;

    for(i = p; i->prox != NULL; i = i->prox){
        for(j = i->prox; j != NULL; j = j->prox){
            if(i->expoente <= j->expoente){
                aux_expo = j->expoente;
                aux_coef = j->coeficiente;
                j->expoente = i->expoente;
                j->coeficiente = i->coeficiente;
                i->expoente = aux_expo;
                i->coeficiente = aux_coef;
            }
        }
  }
}
