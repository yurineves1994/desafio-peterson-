export interface IEmpresa {
  id: number;
  razaoSocial: string;
  nomeFantasia: string;
  email: string;
  cnpj: string;
  enderecos: IEndereco[];
}

export interface IEndereco {
  id: number;
  logradouro: string;
  bairro: string;
  cidade: string;
  estado: string;
  latitude: string;
  longitude: string;
  cep: string;
}
