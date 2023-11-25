import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAddressesModalComponent } from './list-addresses-modal.component';

describe('ListAddressesModalComponent', () => {
  let component: ListAddressesModalComponent;
  let fixture: ComponentFixture<ListAddressesModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListAddressesModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAddressesModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
