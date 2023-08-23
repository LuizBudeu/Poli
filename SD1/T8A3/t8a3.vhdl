library ieee;
use ieee.numeric_bit.all;
use std.textio.all;

entity rom_arquivo_generica is
    generic (
        addressSize : natural := 4;
        wordSize : natural := 8;
        datFileName : string := " rom . dat "
    );
    port (
        addr : in bit_vector ( addressSize -1 downto 0);
        data : out bit_vector ( wordSize -1 downto 0)
    );
end rom_arquivo_generica ;

architecture arq of rom_arquivo_generica is 
    constant depth: natural := 2**addressSize;
    type mem_t is array (0 to depth -1) of bit_vector(wordSize -1 downto 0);

    impure function init_mem(nomearq : in string) return mem_t is
        file mif_file : text open read_mode is nomearq;
        variable mif_line : line;
        variable temp_bv : bit_vector(wordSize -1 downto 0);
        variable temp_mem : mem_t;
    begin
        for i in mem_t'range loop
            readline(mif_file, mif_line);
            read(mif_line, temp_bv);
            temp_mem(i) := temp_bv;
        end loop;
        return temp_mem;
    end function;
    
    constant mem : mem_t := init_mem(datFileName);

    begin
        data <= mem(to_integer(unsigned(addr)));

end arq;