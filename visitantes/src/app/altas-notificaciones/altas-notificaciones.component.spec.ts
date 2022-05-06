import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltasNotificacionesComponent } from './altas-notificaciones.component';

describe('AltasNotificacionesComponent', () => {
  let component: AltasNotificacionesComponent;
  let fixture: ComponentFixture<AltasNotificacionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AltasNotificacionesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AltasNotificacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
