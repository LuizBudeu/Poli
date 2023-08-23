entity painel_seguranca is
     port (
          clock                     : in  std_logic;
          cancelar                  : in  std_logic;
          enter                     : in  std_logic;
          botoes                    : in  std_logic_vector(3 downto 0);
          entradabdNormal           : in  std_logic_vector(3 downto 0);
          entradabdPanico           : in  std_logic_vector(3 downto 0);
          saidacom                  : out std_logic_vector(3 downto 0);
          espere                    : out std_logic;
          abrir                     : out std_logic;
          errou                     : out std_logic;
          alarme                    : out std_logic;
          db_contagem               : out std_logic_vector(6 downto 0);
          db_memoria                : out std_logic_vector(6 downto 0);
          db_panico                 : out std_logic_vector(6 downto 0);
          db_ultimo                 : out std_logic_vector(6 downto 0);
          db_ultimop                : out std_logic_vector(6 downto 0);
          db_cargap                 : out std_logic_vector(6 downto 0);
          db_jogada_feita           : out std_logic;
          db_chavesIgualMemoria     : out std_logic;
          db_sPanicoCorreta         : out std_logic
     );
end entity;

