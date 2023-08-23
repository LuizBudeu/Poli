library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.math_real.all;

entity fluxo_dados is
  port (
       clock                         : in  std_logic;
       zeraS                         : in  std_logic;
       contaS                        : in  std_logic;
       zeraE                         : in  std_logic;
       contaE                        : in  std_logic;
       zeraTMR                       : in  std_logic;
       contaTMR                      : in  std_logic;
       escreve                       : in  std_logic;
       limpaR                        : in  std_logic;
       registraR                     : in  std_logic;
       limpaM                        : in  std_logic;
       registraM                     : in  std_logic;
       botoes                        : in  std_logic_vector (3 downto 0);
       chavesIgualMemoria            : out std_logic;
       enderecoIgualSequencia        : out std_logic;
       enderecoMenorOuIgualSequencia : out std_logic;
       fimE                          : out std_logic;
       fimS                          : out std_logic;
       fimTMR                        : out std_logic;
       jogada_feita                  : out std_logic;
       db_contagem                   : out std_logic_vector (3 downto 0);
       db_sequencia                  : out std_logic_vector (3 downto 0);
       db_memoria                    : out std_logic_vector (3 downto 0);
       db_jogada                     : out std_logic_vector (3 downto 0)
  );
end entity;

architecture estrutural of fluxo_dados is

  signal s_endereco        : std_logic_vector (3 downto 0);
  signal s_sequencia       : std_logic_vector (3 downto 0);
  signal s_dado            : std_logic_vector (3 downto 0);
  signal s_jogada          : std_logic_vector (3 downto 0);
  signal not_zeraE         : std_logic;
  signal not_zeraS         : std_logic;
  signal not_escreve      : std_logic;
  signal not_registraR     : std_logic;
  signal not_registraM     : std_logic;
  signal s_chaveacionada   : std_logic;
  signal reset_edge        : std_logic;

  component contador_163
    port (
        clock : in  std_logic;
        clr   : in  std_logic;
        ld    : in  std_logic;
        ent   : in  std_logic;
        enp   : in  std_logic;
        D     : in  std_logic_vector (3 downto 0);
        Q     : out std_logic_vector (3 downto 0);
        rco   : out std_logic
    );
  end component;

  component comparador_85
    port (
    i_A3   : in  std_logic;
    i_B3   : in  std_logic;
    i_A2   : in  std_logic;
    i_B2   : in  std_logic;
    i_A1   : in  std_logic;
    i_B1   : in  std_logic;
    i_A0   : in  std_logic;
    i_B0   : in  std_logic;
    i_AGTB : in  std_logic;
    i_ALTB : in  std_logic;
    i_AEQB : in  std_logic;
    o_AGTB : out std_logic;
    o_ALTB : out std_logic;
    o_AEQB : out std_logic
    );
  end component;

  component ram_16x4 is
    port (
       clk          : in  std_logic;
       endereco     : in  std_logic_vector(3 downto 0);
       dado_entrada : in  std_logic_vector(3 downto 0);
       we           : in  std_logic;
       ce           : in  std_logic;
       dado_saida   : out std_logic_vector(3 downto 0)
    );
  end component;

  component registrador_173 is
      port (
          clock : in  std_logic;
          clear : in  std_logic;
          en1   : in  std_logic;
          en2   : in  std_logic;
          D     : in  std_logic_vector (3 downto 0);
          Q     : out std_logic_vector (3 downto 0)
     );
  end component;

 component edge_detector is
      port (
          clock  : in  std_logic;
          reset  : in  std_logic;
          sinal  : in  std_logic;
          pulso  : out std_logic
      );
 end component;

 component contador_m is
     generic (
         constant M: integer := 100 -- modulo do contador
     );
     port (
         clock   : in  std_logic;
         zera_as : in  std_logic;
         zera_s  : in  std_logic;
         conta   : in  std_logic;
         Q       : out std_logic_vector(natural(ceil(log2(real(M))))-1 downto 0);
         fim     : out std_logic;
         meio    : out std_logic
     );
 end component;

begin
  not_zeraE   <= not zeraE;
  not_zeraS   <= not zeraS;
  not_registraR <= not registraR;
  not_registraM <= not registraM;
  not_escreve <= not escreve;
  s_chaveacionada <= botoes(3) or botoes(2) or botoes(1) or botoes(0);
  reset_edge <= not(s_chaveacionada) or (zeraE and limpaR);

  contadorendereco: contador_163
    port map (clock, not_zeraE, '1', '1', contaE, "0000", s_endereco, fimE);

  contadorsequencia: contador_163
    port map (clock, not_zeraS, '1', '1', contaS, "0000", s_sequencia, fimS);

  Timer: contador_m
    generic map(500)
    port map (clock, zeraTMR, '0', contaTMR, open, fimTMR, open); --zera de modo assincrono

  comparadorjogadas: comparador_85
    port map (s_dado(3), s_jogada(3), s_dado(2), s_jogada(2), s_dado(1), s_jogada(1), s_dado(0), s_jogada(0), '0', '0', '1', open, open, chavesIgualMemoria);

  comparadorsequencia: comparador_85
    port map (s_sequencia(3), s_endereco(3), s_sequencia(2), s_endereco(2), s_sequencia(1), s_endereco(1), s_sequencia(0), s_endereco(0), '0', '0', '1', enderecoMenorOuIgualSequencia, open, enderecoIgualSequencia);

  memoria: ram_16x4  -- usar para Quartus
  --memoria: entity work.ram_16x4(ram_modelsim) -- usar para ModelSim
    port map (clock, s_endereco, s_jogada, not_escreve, '0', s_dado);

  registradorbotoes: registrador_173
    port map(clock, limpaR, not_registraR, '0', botoes, s_jogada);

  registradormemoria: registrador_173
    port map(clock, limpaM, not_registraM, '0', s_dado, db_memoria);

  JGD: edge_detector
    port map(clock, reset_edge, s_chaveacionada, jogada_feita);

  db_contagem   <= s_endereco;
  db_sequencia  <= s_sequencia;
  db_jogada     <= s_jogada;

end estrutural;
