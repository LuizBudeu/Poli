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
  18/11/2020
  Cristina Gomes
  EP4

  main.c

  Refer�ncias: Com exce��o das rotinas fornecidas no esqueleto e em sala
  de aula, caso voc� tenha utilizado alguma refer�ncia, liste-as abaixo
  para que o seu programa n�o seja considerada pl�gio.

  - 'void queueInit(int m, int n);'
    'int queueEmpty();'
    'void queuePut(int item);'
    'int queueGet();'
    'void queueFree();'         adaptadas dos slides

 \__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__\__
*/

#include <stdio.h>
#include <stdlib.h>

/* DECLARA��ES GLOBAIS */
static int *q;
static int ini;
static int fim;
static int visitados[200];
static int k;
static int *cenouras;
static int t;
static char **lab;

/* DECLARA��ES DE PROT�TIPOS DE FUN��O */
void queueInit(int m, int n);
int queueEmpty();
void queuePut(int item);
int queueGet();
void queueFree();
int jaVisitado(int casa);
int ehCenoura(int casa);
int pedeOpcao();
void salvaArquivoEDados(int *m, int *n);
void printLabirinto(int *m, int *n, int resolvido);
void resolveLabirintoEDaComprimento(int *m, int *n, int *iniX, int *iniY);

/* FUNÇÃO MAIN */
int main()
{
    int m, n, i, j, iniX, iniY, opcao, resolvido, primeira_vez = 1, entrou_zero = 0;

    k = 0;
    visitados[k++] = -1;

    printf("*************************\nCarrotsvile search engine\n*************************\n\n");
    opcao = pedeOpcao();

    while (opcao != 2)
    {
        if (opcao == 0)
        {

            /* Para liberar a mem�ria corretamente */
            if (!primeira_vez)
            {
                free(cenouras);
                primeira_vez = 0;
            }

            /* Primeiro salvamos o labirinto e seus dados correspondetemente */
            entrou_zero = 1;
            resolvido = 0;
            salvaArquivoEDados(&m, &n);
            printLabirinto(&m, &n, resolvido);

            /* Depois resolve-se o labirinto */
            resolveLabirintoEDaComprimento(&m, &n, &iniX, &iniY);
            resolvido = 1;

            /* Voltamos o labirinto como era originalmente */
            lab[iniX - 1][iniY - 1] = 'H';
            printLabirinto(&m, &n, resolvido);
            lab[iniX - 1][iniY - 1] = '0';
            for (i = 0; i < m; i++)
            {
                for (j = 0; j < n; j++)
                {
                    if (lab[i][j] == '*')
                    {
                        lab[i][j] = '0';
                    }
                }
            }
        }

        else if (opcao == 1)
        {

            if (entrou_zero)
            {
                /* Resolve-se o labirinto */
                resolvido = 0;
                resolveLabirintoEDaComprimento(&m, &n, &iniX, &iniY);
                resolvido = 1;

                /* Voltamos o labirinto como era originalmente */
                lab[iniX - 1][iniY - 1] = 'H';
                printLabirinto(&m, &n, resolvido);
                lab[iniX - 1][iniY - 1] = '0';
                for (i = 0; i < m; i++)
                {
                    for (j = 0; j < n; j++)
                    {
                        if (lab[i][j] == '*')
                        {
                            lab[i][j] = '0';
                        }
                    }
                }
            }

            else
            {
                printf("Carregue um labirinto primeiro!\n");
            }
        }

        else if (opcao == 2)
        {
            break;
        }

        else
        {
            printf("Opcao invalida!\n");
        }

        printf("\n");
        opcao = pedeOpcao();
    }

    printf("Tchau, Herbert!\n");

    /* Para liberar a mem�ria corretamente */
    if (entrou_zero)
    {
        for (i = 0; i < m; i++)
        {
            free(lab[i]);
        }
    }

    return 0;
}

/* FUN��ES AUXILIARES */

/* Fun��es para manipular a fila */
/*****************************************************/
void queueInit(int m, int n)
{
    q = malloc(n * m * sizeof(int));
    ini = fim = 0;
}

int queueEmpty()
{
    return ini == fim;
}

void queuePut(int item)
{
    q[fim++] = item;
}

int queueGet()
{
    return q[ini++];
}

void queueFree()
{
    free(q);
}
/*****************************************************/

/* Fun��es �teis do algoritmo */
/*****************************************************/
int jaVisitado(int casa)
{
    int i;

    for (i = k - 1; visitados[i] != -1 && i >= 0; i--)
    {
        if (visitados[i] == casa)
            return 1;
    }

    return 0;
}

int ehCenoura(int casa)
{
    int i;

    for (i = t - 1; cenouras[i] != -1 && i >= 0; i--)
    {
        if (cenouras[i] == casa)
            return 1;
    }

    return 0;
}
/*****************************************************/

/* Fun��es gerais */
/*****************************************************/
void printLabirinto(int *m, int *n, int resolvido)
{
    int mm, nn, i, j;

    mm = *m;
    nn = *n;

    if (!resolvido)
    {
        printf("\nLabirinto:\n");
        for (i = 0; i < mm; i++)
        {
            for (j = 0; j < nn; j++)
            {
                if (lab[i][j] == 'C')
                {
                    printf("0 ");
                }
                else
                {
                    printf("%c ", lab[i][j]);
                }
            }
            printf("\n");
        }
    }

    else
    {
        printf("\n");
        for (i = 0; i < mm; i++)
        {
            for (j = 0; j < nn; j++)
            {
                printf("%c ", lab[i][j]);
            }
            printf("\n");
        }
    }
}

/* Salva arquivo e dados e devolve o labirinto como uma matriz de char */
void salvaArquivoEDados(int *m, int *n)
{
    FILE *fp;
    int i, j, mm, nn, qnt_cenouras, posX_cenoura, posY_cenoura, **lab_int;
    char nomearq[100];

    printf("Digite o nome do arquivo: ");
    scanf("%s", nomearq);

    if (!(fp = fopen(nomearq, "r")))
    {
        printf("Erro na abertura do arquivo %s\n", nomearq);
        exit(0);
    }
    fscanf(fp, "%d %d", &mm, &nn);

    /* Inicializando 'lab_int' */
    lab_int = malloc(sizeof(int *) * mm);
    for (i = 0; i < mm; i++)
        lab_int[i] = malloc(sizeof(int) * nn);

    /* Primeiro salvamos os n�meros do labirinto como int */
    for (i = 0; i < mm; i++)
    {
        for (j = 0; j < nn; j++)
        {
            fscanf(fp, "%d", &lab_int[i][j]);
        }
    }

    /* Depois inicializando 'lab'*/
    lab = malloc(sizeof(int *) * mm);
    for (i = 0; i < mm; i++)
        lab[i] = malloc(sizeof(int) * nn);

    /* Depois convertemos para char */
    for (i = 0; i < mm; i++)
    {
        for (j = 0; j < nn; j++)
        {
            if (lab_int[i][j] == 0)
                lab[i][j] = '0';
            else if (lab_int[i][j] == 1)
                lab[i][j] = '1';
            else
                printf("Erro com labirinto"); /* S� para seguran�a */
        }
    }

    fscanf(fp, "%d", &qnt_cenouras);
    for (i = 0; i < qnt_cenouras; i++)
    {
        fscanf(fp, "%d %d", &posX_cenoura, &posY_cenoura);
        lab[posX_cenoura - 1][posY_cenoura - 1] = 'C';
    }

    /* Salvando os lugares das cenouras */
    cenouras = malloc((qnt_cenouras + 1) * sizeof(int));
    t = 0;
    cenouras[t++] = -1;
    for (i = 0; i < mm; i++)
    {
        for (j = 0; j < nn; j++)
        {
            if (lab[i][j] == 'C')
            {
                cenouras[t++] = i * nn + j + 1;
            }
        }
    }

    *m = mm;
    *n = nn;

    for (i = 0; i < mm; i++)
    {
        free(lab_int[i]);
    }
    fclose(fp);
}

void resolveLabirintoEDaComprimento(int *m, int *n, int *iniX, int *iniY)
{
    int comprimento, iniXX, iniYY, i, j, mm, nn, atual, cenoura, caminho;
    int *ultimos;

    mm = *m;
    nn = *n;

    printf("\n");
    printf("Digite a posicao inicial do Herbert: ");
    scanf("%d %d", &iniXX, &iniYY);

    /* Em 'ultimos' vamos salvar o anterior da casa atual para cada casa */
    ultimos = malloc((mm * nn + 1) * sizeof(int));
    for (i = 0; i < mm; i++)
    {
        ultimos[i] = -1;
    }

    ultimos[nn * (iniXX - 1) + iniYY] = 0;

    /* O algoritmo em si */
    queueInit(mm, nn);
    i = iniXX - 1;
    j = iniYY - 1;
    queuePut(nn * (iniXX - 1) + iniYY);
    while (!queueEmpty())
    {
        atual = queueGet();
        if (atual % nn != 0)
        {
            j = atual % nn - 1;
            i = atual / nn;
        }
        else
        {
            j = nn - 1;
            i = atual / nn - 1;
        }

        if (ehCenoura(atual))
            break;

        /* Pra direita */
        if (j != nn - 1)
        {
            if (lab[i][j + 1] != '1' && !jaVisitado(atual + 1))
            {
                queuePut(atual + 1);
                ultimos[atual + 1] = atual;
            }
        }

        /* Pra esquerda */
        if (j != 0)
        {
            if (lab[i][j - 1] != '1' && !jaVisitado(atual - 1))
            {
                queuePut(atual - 1);
                ultimos[atual - 1] = atual;
            }
        }

        /* Pra cima */
        if (i != 0)
        {
            if (lab[i - 1][j] != '1' && !jaVisitado(atual - nn))
            {
                queuePut(atual - nn);
                ultimos[atual - nn] = atual;
            }
        }

        /* Pra baixo */
        if (i != mm - 1)
        {
            if (lab[i + 1][j] != '1' && !jaVisitado(atual + nn))
            {
                queuePut(atual + nn);
                ultimos[atual + nn] = atual;
            }
        }

        visitados[k++] = atual;
    }

    k = 0;
    visitados[k++] = -1;
    queueFree();

    if (ehCenoura(atual))
    { /* Quer dizer que achou cenoura */
        cenoura = atual;
        caminho = ultimos[cenoura];
        comprimento = 0;
        if (ultimos[cenoura] != -1)
        {

            if (caminho != 0)
            {
                if (caminho % nn != 0)
                {
                    j = caminho % nn - 1;
                    i = caminho / nn;
                }
                else
                {
                    j = nn - 1;
                    i = caminho / nn - 1;
                }
            }
            lab[i][j] = '*';

            while (caminho != 0)
            {
                caminho = ultimos[caminho];
                comprimento++;

                if (caminho != 0)
                {
                    if (caminho % nn != 0)
                    {
                        j = caminho % nn - 1;
                        i = caminho / nn;
                    }
                    else
                    {
                        j = nn - 1;
                        i = caminho / nn - 1;
                    }
                }

                lab[i][j] = '*';
            }

            printf("\nO Herbert achou uma cenoura em %d passos!\n", comprimento);
        }
    }

    else
    { /* Se nao achou cenoura */
        printf("\nO Herbert nao achou nenhuma cenoura!!! :(\n");
    }

    *iniX = iniXX;
    *iniY = iniYY;

    free(ultimos);
}

int pedeOpcao()
{
    int opcao;

    printf("0: carregar um novo labirinto e posicao inicial do Herbert\n");
    printf("1: dar nova posicao inicial do Herbert no mesmo labirinto\n");
    printf("2: sair do programa\n\n");

    printf("Digite a opcao desejada: ");
    scanf("%d", &opcao);
    printf("\n");

    return opcao;
}
/*****************************************************/
