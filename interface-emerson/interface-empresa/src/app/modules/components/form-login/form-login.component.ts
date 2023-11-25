import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EmpresaService } from 'src/app/services/empresa.service';

@Component({
  selector: 'app-form-login',
  templateUrl: './form-login.component.html',
  styleUrls: ['./form-login.component.scss'],
})
export class FormLoginComponent implements OnInit {
  form: FormGroup = this.formBuilder.group({
    username: ['', Validators.required, Validators.email],
    password: ['', Validators.required],
  });

  constructor(
    private empresaService: EmpresaService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {}

  public Logar() {
    if (this.form.valid) {
      console.log('Form Submitted');
      console.log(this.form.value);
      //addEmpresa(this.form.value)
    }
  }
}
