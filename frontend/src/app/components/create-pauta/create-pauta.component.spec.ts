import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePautaComponent } from './create-pauta.component';

describe('CreatePautaComponent', () => {
  let component: CreatePautaComponent;
  let fixture: ComponentFixture<CreatePautaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePautaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePautaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
