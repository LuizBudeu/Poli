library ieee;
use ieee.std_logic_1164.all;

entity fluxo_dados is
   port (
        clock              : in  std_logic;
        zeraC              : in std_logic;
        contaC             : in std_logic;
        escreveM           : in std_logic;
        zeraR              : in  std_logic;
        registraR          : in std_logic;
        chaves             : in  std_logic_vector (3 downto 0);
        chavesIgualMemoria : out std_logic;
        fimC               : out std_logic;
        db_contagem        : out std_logic_vector (3 downto 0);
        db_memoria         : out std_logic_vector (3 downto 0);
        db_chaves          : out std_logic_vector (3 downto 0)
   );
end entity fluxo_dados;

architecture estrutural of fluxo_dados is

  signal s_endereco    : std_logic_vector (3 downto 0);
  signal s_dado        : std_logic_vector (3 downto 0);
  signal s_chaves      : std_logic_vector (3 downto 0);
  signal not_zeraC     : std_logic;
  signal not_escreveM  : std_logic;
  signal not_registraR  : std_logic;

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

begin
  not_zeraC <= not zeraC;
  not_registraR <= not registraR;
  not_escreveM <= not escreveM;

  contador: contador_163
    port map (clock, not_zeraC, '1', '1', contaC, "0000", s_endereco, fimC);

  comparador: comparador_85
    port map (s_dado(3), s_chaves(3), s_dado(2), s_chaves(2), s_dado(1), s_chaves(1), s_dado(0), s_chaves(0), '0', '0', '1', open, open, chavesIgualMemoria);


  memoria: ram_16x4  -- usar para Quartus
  --memoria: entity work.ram_16x4(ram_modelsim) -- usar para ModelSim
    port map (clock, s_endereco, s_chaves, not_escreveM, '0', s_dado);

  registrador: registrador_173
    port map(clock, zeraR, not_registraR, '0', chaves, s_chaves);

  db_contagem <= s_endereco;
  db_memoria  <= s_dado;
  db_chaves   <= s_chaves;

end estrutural;
