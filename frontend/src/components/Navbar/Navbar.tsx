import Link from "next/link";
import * as React from "react";
import Image from "next/image";

type INavbarProps = {};

export function Navbar(props: INavbarProps) {
	return (
		<div className="flex items-center justify-between sm:px-12 px-2 md:px-24">
			<Link href="/" className="flex items-center justify-center h-2=">
				<img src="/logo.png" alt="LeetsCode" className="h-full" />
				<div className="flex item-center">
					<button className="bg-brand-orange">Sign In</button>
				</div>
			</Link>
		</div>
	);
}
