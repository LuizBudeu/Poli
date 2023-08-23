-------------------------------------------------------------------
-- Arquivo   : comparador_85.vhd
-- Projeto   : Experiencia 02 - Um Fluxo de Dados Simples
-------------------------------------------------------------------
-- Descricao : comparador binario de 4 bits
--             similar ao CI 7485
--             baseado em descricao criada por Edson Gomi (11/2017)
-------------------------------------------------------------------
-- Revisoes  :
--     Data        Versao  Autor             Descricao
--     02/01/2021  1.0     Edson Midorikawa  criacao
-------------------------------------------------------------------

library ieee; --importa a biblioteca de descrições ieee
use ieee.std_logic_1164.all; --declara o uso das funções do pacote std_logic_1164

entity comparador_85 is  --declara a entidade comparador_85
  port ( --inicia a descrição de portas da entidade
    i_A3   : in  std_logic; --declara a entrada do MSB do dado A do tipo std_logic
    i_B3   : in  std_logic; --declara a entrada do MSB do dado B do tipo std_logic
    i_A2   : in  std_logic; --declara a entrada do segundo MSB do dado A do tipo std_logic
    i_B2   : in  std_logic; --declara a entrada do segundo MSB do dado B do tipo std_logic
    i_A1   : in  std_logic; --declara a entrada do segundo LSB do dado A do tipo std_logic
    i_B1   : in  std_logic; --declara a entrada do segundo LSB do dado B do tipo std_logic
    i_A0   : in  std_logic; --declara a entrada do LSB do dado A do tipo std_logic
    i_B0   : in  std_logic; --declara a entrada do LSB do dado B do tipo std_logic
    i_AGTB : in  std_logic; --declara a entrada de cascateamento, que sinaliza que, até aqui, A é maior do que B do tipo std_logic
    i_ALTB : in  std_logic; --declara a entrada de cascateamento, que sinaliza que, até aqui, A é menor do que B do tipo std_logic
    i_AEQB : in  std_logic; --declara a entrada de cascateamento, que sinaliza que, até aqui, os bits de A e B são iguais, do tipo std_logic
    o_AGTB : out std_logic; --declara a saída que sinaliza que A é maior do que B do tipo std_logic
    o_ALTB : out std_logic; --declara a saída que sinaliza que A é menor do que B do tipo std_logic
    o_AEQB : out std_logic  --declara a saída que sinaliza que A é igual a que B do tipo std_logic
  ); --finaliza a declaração de portas
end entity comparador_85; --finaliza a declaração da entidade

architecture dataflow of comparador_85 is --declara a arquitetura 'dataflow' da entidade
  signal agtb : std_logic; --declara o sinal interno A>B do tipo std_logic, que indica que A é maior do que B
  signal aeqb : std_logic; --declara o sinal interno A=B do tipo std_logic, que indica que A é igual a que B
  signal altb : std_logic; --declara o sinal interno A<B do tipo std_logic, que indica que A é menor do que B
begin --começa a descrição da arquitetura
  -- equacoes dos sinais: pagina 462, capitulo 6 do livro-texto
  -- Wakerly, J.F. Digital Design - Principles and Practice, 4th Edition
  -- veja tambem datasheet do CI SN7485 (Function Table)
  agtb <= (i_A3 and not(i_B3)) or --lógica do sinal interno A>B; indica que, para A>B alto, A3 deve ser alto e B3, baixo
          (not(i_A3 xor i_B3) and i_A2 and not(i_B2)) or --ou, A3 deve ser igual a B3 e A2 deve ser alto e B2, baixo
          (not(i_A3 xor i_B3) and not(i_A2 xor i_B2) and i_A1 and not(i_B1)) or --ou, A3 deve ser igual a B3, A2 igual a B2 e A1 deve ser alto e B1, baixo
          (not(i_A3 xor i_B3) and not(i_A2 xor i_B2) and not(i_A1 xor i_B1) and i_A0 and not(i_B0)); --ou, A3 deve ser igual a B3, A2 igual a B2, A1 igual a B1 e A0 deve ser alto e B0, baixo
  aeqb <= not((i_A3 xor i_B3) or (i_A2 xor i_B2) or (i_A1 xor i_B1) or (i_A0 xor i_B0)); --lógica do sinal interno A=B; indica que, para A=B alto, todos os bits AX devem ser iguais aos seus respectivos BX
  altb <= not(agtb or aeqb); --lógica do sinal interno A<B; indica que, para A<B alto, os sinais internos A>B e A=B devem ser baixos
  -- saidas
  o_AGTB <= agtb or (aeqb and (not(i_AEQB) and not(i_ALTB))); --lógica da saída A>B; indica que, para A>B alto, o sinal interno A>B deve estar alto ou o sinal interno A=B deve estar alto e as entradas A=B e A<B baixas
  o_ALTB <= altb or (aeqb and (not(i_AEQB) and not(i_AGTB))); --lógica da saída A<B; indica que, para A<B alto, o sinal interno A<B deve estar alto ou o sinal interno A=B deve estar alto e as entradas A=B e A>B baixas
  o_AEQB <= aeqb and i_AEQB; --lógica da saída A=B; indica que, para A=B alto, o sinal interno A=B e a entrada A=B devem estar altos

end architecture dataflow; --finaliza a descrição da arquitetura
