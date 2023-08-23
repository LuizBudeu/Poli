entity cache is
    generic (
        address_size_in_bits : natural := 16 ;
        cache_size_in_bits : natural := 8 ;
        word_size_in_bits : natural := 8
    ) ;
    port (
        ck, enable, write_enable : in bit;
        addr_i : in bit_vector(address_size_in_bits−1 downto 0 ) ;
        data_i : in bit_vector(word_size_in_bits−1 downto 0 ) ;
        data_o : out bit_vector(word_size_in_bits−1 downto 0 ) ;
        bsy : out bit ;
        nl_data_i : in std_logic_vector (word_size_in_bits−1 downto 0 ) ;
        nl_enable, nl_write_enable : out bit ;
        nl_bsy : in bit
    ) ;
end cache ;



architecture arq of cache is 

    begin 
    process(ck)
        begin

    end process;

end arq;















