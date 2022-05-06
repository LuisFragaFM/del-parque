import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarPaqueteriaComponent } from './registrar-paqueteria.component';

describe('RegistrarPaqueteriaComponent', () => {
  let component: RegistrarPaqueteriaComponent;
  let fixture: ComponentFixture<RegistrarPaqueteriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarPaqueteriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarPaqueteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
