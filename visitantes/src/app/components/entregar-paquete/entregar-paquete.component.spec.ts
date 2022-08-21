import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EntregarPaqueteComponent } from './entregar-paquete.component';

describe('EntregarPaqueteComponent', () => {
  let component: EntregarPaqueteComponent;
  let fixture: ComponentFixture<EntregarPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntregarPaqueteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntregarPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
