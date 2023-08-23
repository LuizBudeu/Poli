typedef void *polinomio;

polinomio cria();

polinomio leia();

polinomio copia(polinomio);

void impr(char, polinomio);

polinomio soma(polinomio, polinomio);

polinomio subt(polinomio, polinomio);

polinomio nega(polinomio);

polinomio mult(polinomio, polinomio);

polinomio quoc(polinomio, polinomio);

polinomio rest(polinomio, polinomio);

void libera(polinomio);
