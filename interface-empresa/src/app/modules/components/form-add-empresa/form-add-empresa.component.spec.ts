import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddEmpresaComponent } from './form-add-empresa.component';

describe('FormAddEmpresaComponent', () => {
  let component: FormAddEmpresaComponent;
  let fixture: ComponentFixture<FormAddEmpresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormAddEmpresaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAddEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
