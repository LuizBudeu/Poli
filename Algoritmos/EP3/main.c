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
  22/10/2020
  Cristina Gomes
  EP3

  main.c

  Refer�ncias: Com exce��o das rotinas fornecidas no esqueleto e em sala
  de aula, caso voc� tenha utilizado alguma refer�ncia, liste-as abaixo
  para que o seu programa n�o seja considerada pl�gio.

  - 'char *infixaParaPosfixa(char *inf)' adaptada dos slides

 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "polinomios.h"
#include "polinomios.c"

char *infixaParaPosfixa(char *inf);

int main()
{
    char opcao, letra;
    char expr_total[100];
    char *expr;
    char *expr_posf;
    int i, j, t, n;
    polinomio var[26], poli_stack[100], expr_posf_poli[100];

    /* Primeiro preenchemos todas as posi��es com polin�mios nulos */
    for (i = 0; i < 26; i++)
        var[i] = cria();

    printf("Digite uma expressao ou quit para sair do programa:\n");
    scanf("%s", expr_total);
    letra = expr_total[0];
    opcao = expr_total[1];
    expr = &expr_total[2];

    while (strcmp(expr_total, "quit") != 0)
    {
        switch (opcao)
        {
        case '?':
            impr(letra, var[letra - 97]); /* A posi��o [letra-97] corresponde � ordem alfab�tica, com o 'a' come�ando em 0 */
            printf("\n");
            break;

        case ':':
            var[letra - 97] = leia();
            impr(letra, var[letra - 97]);
            printf("\n");
            break;

        case '=':
            /* Primeiro transformamos a express�o em p�sfixa */
            expr_posf = infixaParaPosfixa(expr);

            /* Depois empilhamos todos os polin�mios em suas posi��es correspondentes, sem contar os operadores */
            n = strlen(expr_posf);
            for (i = j = 0; i < n; i++)
            {
                if (expr_posf[t] >= 'a' && expr_posf[t] <= 'z')
                {
                    expr_posf_poli[j] = var[expr_posf[i] - 97];
                    j++;
                }
            }

            /* Depois resolvemos a express�o p�sfixa */
            i = 0;
            for (t = 0; t < n; t++)
            {
                if (expr_posf[t] == '+')
                {
                    poli_stack[i - 2] = soma(poli_stack[i - 2], poli_stack[i - 1]);
                    i = i - 1;
                }

                else if (expr_posf[t] == '-')
                {
                    poli_stack[i - 2] = subt(poli_stack[i - 2], poli_stack[i - 1]);
                    i = i - 1;
                }

                else if (expr_posf[t] == '*')
                {
                    poli_stack[i - 2] = mult(poli_stack[i - 2], poli_stack[i - 1]);
                    i = i - 1;
                }

                else if (expr_posf[t] == '/')
                {
                    poli_stack[i - 2] = quoc(poli_stack[i - 2], poli_stack[i - 1]);
                    i = i - 1;
                }

                else if (expr_posf[t] == '%')
                {
                    poli_stack[i - 2] = rest(poli_stack[i - 2], poli_stack[i - 1]);
                    i = i - 1;
                }

                else if (expr_posf[t] == '~')
                {
                    poli_stack[i - 1] = nega(poli_stack[i - 1]);
                }

                else
                {
                    poli_stack[i++] = expr_posf_poli[t];
                }
            }

            /* Guardamos o polin�mio resultante e liberamos a express�o p�sfixa */
            var[letra - 97] = copia(poli_stack[i - 1]);
            impr(letra, var[letra - 97]);
            free(expr_posf);

            printf("\n");
            break;

        default:
            printf("Opcao invalida!\n");
        }

        printf("Digite uma expressao ou quit para sair do programa:\n");
        scanf("%s", expr_total);
        letra = expr_total[0];
        opcao = expr_total[1];
        expr = &expr_total[2];
        printf("\n");
    }

    /* No final, liberamos toda a mem�ria */
    for (i = 0; i < 26; i++)
        libera(var[i]);

    printf("Tchau!\n");
    return 0;
}

char *infixaParaPosfixa(char *inf)
{
    char *posf; /* expressao polonesa */
    int n = strlen(inf);
    int i;   /* percorre infixa */
    int j;   /* percorre posfixa */
    char *s; /* pilha */
    int t;   /* topo da pilha */
    char x;  /* item do topo da pilha */

    /*aloca area para expressao polonesa*/
    posf = malloc((n + 1) * sizeof(char));
    s = malloc(n * sizeof(char));
    t = 0;

    /* examina cada item da infixa */
    for (i = j = 0; i < n; i++)
    {
        switch (inf[i])
        {
        case '(':
            s[t++] = inf[i];
            break;

        case ')':
            while ((x = s[--t]) != '(')
                posf[j++] = x;
            break;

        case '+':
        case '-':
            while (t != 0 && (x = s[t - 1]) != '(')
                posf[j++] = s[--t];
            s[t++] = inf[i];
            break;

        case '*':
        case '/':
        case '%':
            while (t != 0 && (x = s[t - 1]) != '(')
                while (t != 0 && x != '+' && x != '-')
                    posf[j++] = s[--t];
            s[t++] = inf[i];
            break;

        case '~':
            while (t != 0 && (x = s[t - 1]) != '(')
                while (t != 0 && x != '+' && x != '-' && x != '*' && x != '/')
                    posf[j++] = s[--t];
            s[t++] = inf[i];
            break;

        default:
            if (inf[i] != ' ' && inf[i] != 13)
                posf[j++] = inf[i];

        } /* fim switch */
    }     /* fim for (i=j=0...) */

    /* desempilha todos os operandos que restaram */
    while (t != 0)
        posf[j++] = s[--t];
    posf[j] = '\0'; /* fim expr polonesa */
    free(s);
    return posf;
} /* fim funcao */
