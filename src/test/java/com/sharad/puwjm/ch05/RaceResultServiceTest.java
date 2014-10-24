package com.sharad.puwjm.ch05;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import org.eclipse.jdt.internal.compiler.ast.Clinit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RaceResultServiceTest {

	RaceResultService raceResultService;
	
	@Mock(name="clientA")
	Client clientA;
	
	@Mock(name="clientB")
	Client clientB;
	@Mock
	Message message;
	
	@Before
	public void Setup(){
		raceResultService = new RaceResultService();
	}

	@Test
	public void subscribedClientShouldReceiveMessage() {
		raceResultService.addSubscriber(clientA);
		raceResultService.send(message);
		verify(clientA).receive(message);
	}

	@Test
	public void messageShouldBeSendToAllSubscribedClients() {
		raceResultService.addSubscriber(clientA);
		raceResultService.addSubscriber(clientB);
		raceResultService.send(message);
		verify(clientA).receive(message);
		verify(clientB).receive(message);

	}
	
	@Test
	public void notSubscribedClientsShouldNotReceiveMessages(){
		raceResultService.send(message);
		verify(clientA, never()).receive(message);
	}
	
	@Test
	public void subscribedClientsShouldReceiveMessagesExactlyOnce(){
		raceResultService.addSubscriber(clientA);
		raceResultService.addSubscriber(clientA);
		raceResultService.send(message);
		verify(clientA).receive(message);
	}
	
	@Test
	public void unsubscribedClientsShouldNotReceiveMessages(){
		raceResultService.addSubscriber(clientA);
		raceResultService.addSubscriber(clientB);
		raceResultService.removeSubscriber(clientA);
		raceResultService.send(message);
		verify(clientA, never()).receive(message);
		
	}
}
