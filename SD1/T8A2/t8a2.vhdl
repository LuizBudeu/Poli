library ieee;
use ieee.numeric_bit.all;
use std.textio.all;

entity rom_arquivo is
    port (
    addr : in bit_vector (3 downto 0);
    data : out bit_vector (7 downto 0)
    );
end rom_arquivo ;

architecture arq of rom_arquivo is 
    type mem_t is array (0 to 15) of bit_vector(7 downto 0);

    impure function init_mem(nomearq : in string) return mem_t is
        file mif_file : text open read_mode is nomearq;
        variable mif_line : line;
        variable temp_bv : bit_vector(7 downto 0);
        variable temp_mem : mem_t;
    begin
        for i in mem_t'range loop
            readline(mif_file, mif_line);
            read(mif_line, temp_bv);
            temp_mem(i) := temp_bv;
        end loop;
        return temp_mem;
    end function;
    
    constant mem : mem_t := init_mem("conteudo_rom_ativ_02_carga.dat");

    begin
        data <= mem(to_integer(unsigned(addr)));

end arq;