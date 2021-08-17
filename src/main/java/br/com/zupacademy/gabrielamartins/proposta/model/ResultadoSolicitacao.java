package br.com.zupacademy.gabrielamartins.proposta.model;

public enum ResultadoSolicitacao {

    COM_RESTRICAO{
        @Override
        public EstadoProposta elegibilidade() {
            return EstadoProposta.NAO_ELEGIVEL;
        }
    },


    SEM_RESTRICAO{
        @Override
        public EstadoProposta elegibilidade() {
            return EstadoProposta.ELEGIVEL;
        }
    };

    public abstract EstadoProposta elegibilidade();
}
