library ieee;
use ieee.numeric_bit.all;

entity uart_rx is 
    generic(
        clk_freq: positive;
        baudrate: positive;
        addr_depth: natural := 1
    );
    port(
        clk: in bit;
        rst: in bit;
        rdata: out bit_vector(7 downto 0);
        rd: in bit;
        rd_en: out bit;
        rx: in bit
    );
end;

architecture arq of uart_rx is 

    signal contador : integer := -1;
    begin 
    process(clk) begin 
        if rst = '1' then 
            rd_en <= '0';
            rdata <= (others => '1');
            contador <= -1;

        else 
            if rising_edge(clk) then 
                if rd = '1' then 
                    if contador = 8 then  -- stop
                        rdata(contador) <= '1';

                    elsif contador = -1 then -- start
                        rdata(contador) <= '0';
                        contador <= contador + 1;
                    
                    else  -- data
                    rdata(contador) <= rx;
                        contador <= contador + 1;

                    end if;

                end if;

            end if;

        end if;
        rd_en <= '0';

    end process;

end arq;