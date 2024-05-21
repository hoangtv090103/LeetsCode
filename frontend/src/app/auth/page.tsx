import { Navbar } from '@/components/Navbar/Navbar';
import * as React from 'react';

export type IAuthPageProps = {};

export default function AuthPage (props: IAuthPageProps) {
  return (
	<div className='bg-gradient-to-b from-gray-600 to-black h-screen relative'>
		<div className='max-w-7xl mx-auto'>
			<Navbar/>
		</div>
		
	</div>
  );
}
