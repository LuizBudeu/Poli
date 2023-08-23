/*
 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__

  AO PREENCHER ESSE CABEÇALHO COM O MEU NOME E O MEU NÚMERO USP,
  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESSE PROGRAMA.
  TODAS AS PARTES ORIGINAIS DESSE EXERCÍCIO-PROGRAMA (EP) FORAM
  DESENVOLVIDAS E IMPLEMENTADAS POR MIM SEGUINDO AS INSTRUÇÕES DESSE EP
  E QUE PORTANTO NÃO CONSTITUEM PLÁGIO. DECLARO TAMBÉM QUE SOU RESPONSÁVEL
  POR TODAS AS CÓPIAS DESSE PROGRAMA E QUE EU NÃO DISTRIBUI OU FACILITEI A
  SUA DISTRIBUIÇÃO. ESTOU CIENTE QUE OS CASOS DE PLÁGIO SÃO PUNIDOS COM
  REPROVAÇÃO DIRETA NA DISCIPLINA.

  Nome: Luiz Guilherme Budeu
  NUSP: 11821639
  Engenharia de Computação
  02/10/2020
  Cristina Gomes
  EP2

  main.c

  Referências: Com exceção das rotinas fornecidas no esqueleto e em sala
  de aula, caso você tenha utilizado alguma referência, liste-as abaixo
  para que o seu programa não seja considerada plágio.

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

  /* Primeiro lemos os dados e os guardamos na lista encadeada, começada por 'ini' */
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

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
  tiraUltimo(ini);

  /* Mas como fizemos a lista não necessariamente numa ordem decrescente, faremos exatamente isso */
  decrescente(ini);

  return ini;
}

polinomio copia(polinomio p) {
  Polinomio *r, *inir, *q;

  r = alocaMonomio();
  inir = r;

  /* Primeiro copiamos o polinômio p para o polinômio r */
  for(q = p; q != NULL; q = q->prox){
    r->coeficiente = q->coeficiente;
    r->expoente = q->expoente;
    r->prox = alocaMonomio();
    r = r->prox;
  }
  r->prox = NULL;

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
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

  /* Primeiro percorremos os dois polinômios, somando-os */
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

  /* Depois caso ainda sobre elementos em algum polinômio, colocamo-nos no final do polinômio r */
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

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
  tiraUltimo(inir);

  return inir;
}

polinomio subt(polinomio p, polinomio q) {
  /* Como fazer 'x - y' é igual a fazer 'x + (-1)y', é possível utilizar as funções 'nega' e 'soma' para realizar a subtração */
  return soma(p, nega(q));
}

polinomio nega(polinomio p) {
  Polinomio *n, *inin, *q;

  n = alocaMonomio();
  inin = n;

  /* Primeiro copiamos o polinômio p para o polinômio n, com o coeficiente negado */
  for(q = p; q != NULL; q = q->prox){
    n->coeficiente = -(q->coeficiente);
    n->expoente = q->expoente;
    n->prox = alocaMonomio();
    n = n->prox;
  }

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
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

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
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

  /* Como o método acima deixa um polinômio nulo a mais como último elemento, removemos esse último elemento */
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

/* Devolve um monômio novo */
polinomio alocaMonomio() {
    Polinomio *p;

    p = mallocSafe(sizeof(Polinomio));
    p->coeficiente = 0;
    p->expoente = 0;
    p->prox = NULL;

    return p;
}

/* Retira o último elemento de uma lista encadeada */
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
