import Link from "next/link";
import * as React from "react";
import Image from "next/image";

type INavbarProps = {};

export function Navbar(props: INavbarProps) {
	return (
		<div className="flex items-center justify-between sm:px-12 px-2 md:px-24">
			<Link href="/" className="flex items-center justify-center h-20">
				<img src="/logo.png" alt="LeetsCode" className="h-full" />
			</Link>
			<div className="flex item-center">
				<button
					type="button"
					className="bg-brand-orange text-white px-2 py-1 sm:px-4 rounded-md text-sm font-medium hover:text-brand-orange hover:bg-white hover:border-2 hover:border-brand-orange border-2 border-transparent transition duration-300 ease-in-out"
				>
					Sign In
				</button>
			</div>
		</div>
	);
}
