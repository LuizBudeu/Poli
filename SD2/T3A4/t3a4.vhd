library ieee;
use ieee.numeric_bit.all;


entity controlunit is 
    port(
        reg2loc: out bit;
        uncondBranch: out bit;
        branch : out bit ;
        memRead : out bit ;
        memToReg : out bit ;
        aluOp : out bit_vector(1 downto 0 ) ;
        memWrite : out bit ;
        aluSrc : out bit ;
        regWrite : out bit ;
        opcode : in bit_vector(10 downto 0)
    );
end entity;


architecture arq of controlunit is 

    begin 
        reg2loc <= '0' when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" else '1';
        uncondBranch <= '0' when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" or
                    opcode = "11111000010" or opcode = "11111000000" else '1';
        branch <= '0' when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" or
                    opcode = "11111000010" or opcode = "11111000000" else '1';
        memRead <= '1' when opcode = "11111000010" else '1';
        memToReg <= '0' when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" else '1';
        aluOp <= "10" when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" else "00" when 
                    opcode = "11111000010" or opcode = "11111000000" else "01";
        memWrite <= '1' when opcode = "11111000000" else '0';
        aluSrc <= '1' when opcode = "11111000010" or opcode = "11111000000" else '0';
        regWrite <= '1' when opcode = "10001011000" or opcode = "11001011000" or opcode = "10001010000" or opcode = "10101010000" or
                    opcode = "11111000010" else '0';

end arq;