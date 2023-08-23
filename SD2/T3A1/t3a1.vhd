library ieee;
use ieee.numeric_bit.all;

entity signExtend is 
    port(
        i: in bit_vector(31 downto 0); --input
        o: out bit_vector(63 downto 0) -- output
    );
end signExtend;


architecture arq of signExtend is 
    signal a: bit_vector(3 downto 0);

    begin

        a <= i(31 downto 28);
        o <= bit_vector(resize(signed(i(20 downto 12)), 64)) when a = "1111" else
            bit_vector(resize(signed(i(23 downto 5)), 64)) when a = "1011" else 
            bit_vector(resize(signed(i(25 downto 0)), 64)) when a = "0001" else (others => '0');

end arq;