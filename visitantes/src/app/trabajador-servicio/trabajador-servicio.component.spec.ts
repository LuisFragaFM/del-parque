import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrabajadorServicioComponent } from './trabajador-servicio.component';

describe('TrabajadorServicioComponent', () => {
  let component: TrabajadorServicioComponent;
  let fixture: ComponentFixture<TrabajadorServicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrabajadorServicioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrabajadorServicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
