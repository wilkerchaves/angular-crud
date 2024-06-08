import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import Produto from '../../../model/Produto';
import { ProdutoService } from '../../services/produto.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-componente1',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './componente1.component.html',
  styleUrl: './componente1.component.css'
})
export class Componente1Component {

  constructor(private servico: ProdutoService) {

  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.selecionar();
  }

  btnCadastrar: boolean = true;

  formulario = new FormGroup({
    id: new FormControl(null),
    nome: new FormControl(''),
    valor: new FormControl(null)

  })


  vetor: Produto[] = []

  selecionar() {
    this.servico.selecionar().subscribe(result => this.vetor = result);
  }

  cadastrar() {
    console.log(this.formulario.value)
    this.servico
      .cadastrar(this.formulario.value as Produto)
      .subscribe(result => {
        this.vetor.push(result);
        this.formulario.reset();
      })
  }

  selecionarProduto(produto: Produto) {
    this.formulario.setValue({
      id: produto.id,
      nome: produto.nome,
      valor: produto.valor
    });
    this.btnCadastrar = false;
  }

  alterar() {
    this.servico
    .alterar(this.formulario.value as Produto)
    .subscribe(
      result => {
        let indiceAlterado = this.vetor.findIndex(
          p => this.formulario.value.id === p.id
        );
        if(result) {
          this.vetor[indiceAlterado] = result;
        }

        this.formulario.reset();
        this.btnCadastrar = true;
      }
    )
  }

  excluir(){
    this.servico
    .remover(this.formulario.value.id)
    .subscribe(()=>{
      let indiceRemovido = this.vetor.findIndex(
        p => p.id === this.formulario.value.id
      )
      this.vetor.splice(indiceRemovido, 1)

      this.formulario.reset()

      this.btnCadastrar = true
    })
  }

}
