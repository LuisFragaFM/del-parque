import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BitacoraTrabajadoresComponent } from './bitacora-trabajadores.component';

describe('BitacoraTrabajadoresComponent', () => {
  let component: BitacoraTrabajadoresComponent;
  let fixture: ComponentFixture<BitacoraTrabajadoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BitacoraTrabajadoresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BitacoraTrabajadoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
