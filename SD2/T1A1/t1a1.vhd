library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_bit.all;

entity ram is 
    generic (
        address_size_in_bits : natural := 64;
        word_size_in_bits : natural := 32;
        delay_in_clocks : positive := 1
    );
    port (
        ck, enable, write_enable : in bit;
        addr : in bit_vector(address_size_in_bits-1 downto 0);
        data : inout std_logic_vector(word_size_in_bits-1 downto 0);
        bsy : out bit
    );
end ram;

architecture arq of ram is
    constant depth : natural := 2**address_size_in_bits;
    type mem_tipo is array (0 to depth-1) of std_logic_vector(word_size_in_bits-1 downto 0);
    signal mem : mem_tipo;
    signal contador : integer range 0 to delay_in_clocks := 0;
    signal temp : bit := '0';

    begin
    process(ck)
        begin
            if enable = '0' then
                bsy <= '0';
                data <= (others => 'Z');

            else 
                if rising_edge(ck) then 
                bsy <= '1';

                    if write_enable = '1' then 
                        if (contador = delay_in_clocks) then
                            mem(to_integer(unsigned(addr))) <= data;
                            bsy <= '0';
                            contador <= 1;
                        
                        else
                            contador <= contador + 1;
                        end if;
                    else  
                        if (contador = delay_in_clocks) then
                            data <= mem(to_integer(unsigned(addr)));
                            bsy <= '0';
                            contador <= 1;

                        else 
                            contador <= contador + 1;
                        end if;

                    end if;

                end if;
                
            end if;


    end process;

end arq;